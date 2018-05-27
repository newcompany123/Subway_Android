package custom.subway.subway.Utility

import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import custom.subway.subway.Model.User

class SubwayApplication : MultiDexApplication() {


    val isLogin = User(this).checkLogin()

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}