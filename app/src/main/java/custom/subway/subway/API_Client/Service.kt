package custom.subway.subway.API_Client

import com.google.gson.JsonObject
import io.reactivex.rxkotlin.Observables
import okhttp3.ResponseBody
import retrofit2.http.GET

interface APIService {

    @GET("www.naver.com")
    fun testt(): Observables<GetToDoListAPIResponse>

}