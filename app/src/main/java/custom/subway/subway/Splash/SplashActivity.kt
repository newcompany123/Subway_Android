package custom.subway.subway.Splash

import android.os.Bundle
import custom.subway.subway.Login.LoginActivity
import custom.subway.subway.R
import custom.subway.subway.Ranking.RankingActivity
import custom.subway.subway.Utility.BaseActivity
import org.jetbrains.anko.intentFor

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        loginPageOrRankingPageBaseOnLoginStatus()
    }

    fun loginPageOrRankingPageBaseOnLoginStatus() {
        when (subwayApplication.isLogin) {
            true -> this@SplashActivity.startActivity(this@SplashActivity.intentFor<RankingActivity>())
            false -> this@SplashActivity.startActivity(this@SplashActivity.intentFor<LoginActivity>())
        }
    }
}
