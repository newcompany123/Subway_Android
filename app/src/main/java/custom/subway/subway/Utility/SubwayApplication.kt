package custom.subway.subway.Utility

import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.kakao.auth.KakaoSDK

class SubwayApplication : MultiDexApplication() {
    companion object {
        var context: SubwayApplication? = null
        fun getSubwayApplicationContext(): SubwayApplication? {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        getSubwayApplicationContext()
        KakaoSDK.init(KakaoSDKAdapter.Companion.SDKAdapter())
        Stetho.initializeWithDefaults(this)
    }

}