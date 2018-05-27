package custom.subway.subway.Utility

import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho

class SubwayApplication : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}