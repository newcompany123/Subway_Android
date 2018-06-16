package custom.subway.subway.API_Client


import com.facebook.stetho.okhttp3.StethoInterceptor
import custom.subway.subway.Model.User
import custom.subway.subway.Utility.Constants
import custom.subway.subway.Utility.SubwayApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


class APIClient(val application: SubwayApplication) {
    private var retrofit: Retrofit? = null

    enum class LogLevel {
        LOG_NOT_NEEDED,
        LOG_REQ_RES,
        LOG_REQ_RES_BODY_HEADERS,
        LOG_REQ_RES_HEADERS_ONLY
    }

    fun getClient(logLevel: LogLevel): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        when (logLevel) {
            LogLevel.LOG_NOT_NEEDED -> interceptor.level = HttpLoggingInterceptor.Level.NONE
            LogLevel.LOG_REQ_RES -> interceptor.level = HttpLoggingInterceptor.Level.BASIC
            LogLevel.LOG_REQ_RES_BODY_HEADERS -> interceptor.level = HttpLoggingInterceptor.Level.BODY
            LogLevel.LOG_REQ_RES_HEADERS_ONLY -> interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        }

        val headerInterceptorForLoginUser = Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            if (application.isLogin) {
                builder.header("Authorization", User.getInstance().token)
            }
            builder.method(original.method(), original.body())
            val request = builder.build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(headerInterceptorForLoginUser)
                .build()
        if (null == retrofit) {
            retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
        }
        return retrofit!!
    }


    fun getAPIService(logLevel: LogLevel = LogLevel.LOG_REQ_RES_BODY_HEADERS) = getClient(logLevel).create(Service::class.java)

}