package custom.subway.subway.MakeRecipe

import android.support.v4.view.ViewPager
import android.view.View


class MakeRecipeViewModel(
    val activity: MakeRecipeActivity
) {


    fun refreshMakeRecipe(view: View, pager: ViewPager) {
        SubwayOnProcess.clearProcess()

    }

}