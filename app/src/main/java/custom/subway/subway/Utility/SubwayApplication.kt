package custom.subway.subway.Utility

import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import custom.subway.subway.Model.User
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SubwayApplication : MultiDexApplication() {


    var isLogin: Boolean = false

    companion object {
        var context: SubwayApplication? = null
        fun getSubwayApplicationContext(): SubwayApplication? {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        SubwayApplication.getSubwayApplicationContext()
//        KakaoSDK.init(KakaoSDKAdapter.Companion.SDKAdapter())
        Stetho.initializeWithDefaults(this)

        observeUserLoginStatus()
    }


    fun observeUserLoginStatus() {
        User.getInstance().loginCheckPublishSubject
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribeBy(
                        onNext = { isLogin = it },
                        onError = { isLogin = false }
                )
    }

}

