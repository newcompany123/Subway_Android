package custom.subway.subway.Utility

import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import custom.subway.subway.Model.User
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class SubwayApplication : MultiDexApplication() {


    var isLogin: Boolean = false

    init {
        observeUserLoginStatus()
        User().checkLogin()

    }

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


        Realm.init(this)
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())

        initialUserLoginCheck()
    }

    val initialUserLoginCheck: () -> (Unit) = {
        User.getInstance().checkLogin()
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

