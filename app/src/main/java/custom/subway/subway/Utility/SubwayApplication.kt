package custom.subway.subway.Utility

import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.kakao.auth.KakaoSDK

import custom.subway.subway.Model.User

class SubwayApplication : MultiDexApplication() {
    val isLogin by lazy { User(this).checkLogin() }

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
        KakaoSDK.init(KakaoSDKAdapter.Companion.SDKAdapter())
        Stetho.initializeWithDefaults(this)
    }
}

