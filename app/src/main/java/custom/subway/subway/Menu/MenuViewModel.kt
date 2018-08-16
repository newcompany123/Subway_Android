package custom.subway.subway.Menu

import android.app.Application
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import custom.subway.subway.MakeRecipe.MakeRecipeActivity
import custom.subway.subway.Ranking.RankingActivity
import org.jetbrains.anko.intentFor

class MenuViewModel private constructor(val application: Application) {

    var rankingActivityIsOn: ObservableField<Boolean> = ObservableField()
    var makeRecipeActivityIsOn: ObservableField<Boolean> = ObservableField()
    var savedARecipeActivityIsOn: ObservableField<Boolean> = ObservableField()
    var menuAcitivtyIsOn: ObservableField<Boolean> = ObservableField()


    companion object {
        var instance: MenuViewModel? = null
        fun getInstance(activity: Application): MenuViewModel {
            if (instance == null) {
                instance = MenuViewModel(activity)
                return instance as MenuViewModel
            } else return instance as MenuViewModel
        }
    }


    init {
        rankingActivityIsOn.set(true)
        makeRecipeActivityIsOn.set(false)
        savedARecipeActivityIsOn.set(false)
        menuAcitivtyIsOn.set(false)
    }

    fun rankingOnClick(view: View) {
        if (rankingActivityIsOn.get() == false) {
            application.startActivity(application.intentFor<RankingActivity>().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        rankingActivityIsOn.set(!rankingActivityIsOn.get()!!)
        makeRecipeActivityIsOn.set(false)
        savedARecipeActivityIsOn.set(false)
        menuAcitivtyIsOn.set(false)
    }

    fun makeRecipeOnClick(view: View) {
        if (makeRecipeActivityIsOn.get() == false) {
            application.startActivity(application.intentFor<MakeRecipeActivity>().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
        rankingActivityIsOn.set(false)
        makeRecipeActivityIsOn.set(!makeRecipeActivityIsOn.get()!!)
        savedARecipeActivityIsOn.set(false)
        menuAcitivtyIsOn.set(false)

    }

    fun savedReceipeOnClick(view: View) {
        rankingActivityIsOn.set(false)
        makeRecipeActivityIsOn.set(false)
        savedARecipeActivityIsOn.set(!savedARecipeActivityIsOn.get()!!)
        menuAcitivtyIsOn.set(false)
    }

    fun menuOnClick(view: View) {
        rankingActivityIsOn.set(false)
        makeRecipeActivityIsOn.set(false)
        savedARecipeActivityIsOn.set(false)
        menuAcitivtyIsOn.set(!menuAcitivtyIsOn.get()!!)
    }


}