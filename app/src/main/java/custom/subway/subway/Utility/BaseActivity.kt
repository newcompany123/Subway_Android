package custom.subway.subway.Utility

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import custom.subway.subway.Menu.MenuViewModel

open class BaseActivity : AppCompatActivity() {

    lateinit protected var subwayApplication: SubwayApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subwayApplication = application as SubwayApplication
    }


    override fun onResume() {
        val menueViewModel = MenuViewModel.getInstance(subwayApplication)
        val am =
            SubwayApplication.context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val cn = am.getRunningTasks(1)[0].topActivity

        if (cn.shortClassName.contains("Ranking")) {
            menueViewModel.rankingActivityIsOn.set(true)
            menueViewModel.makeRecipeActivityIsOn.set(false)
            menueViewModel.savedARecipeActivityIsOn.set(false)
            menueViewModel.menuAcitivtyIsOn.set(false)
        } else if (cn.shortClassName.contains("Make")) {
            menueViewModel.rankingActivityIsOn.set(false)
            menueViewModel.makeRecipeActivityIsOn.set(true)
            menueViewModel.savedARecipeActivityIsOn.set(false)
            menueViewModel.menuAcitivtyIsOn.set(false)
        }

        super.onResume()
    }
}