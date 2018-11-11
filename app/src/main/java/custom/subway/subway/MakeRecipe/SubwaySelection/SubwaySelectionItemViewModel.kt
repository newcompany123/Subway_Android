package custom.subway.subway.MakeRecipe.SubwaySelection

import android.view.View
import custom.subway.subway.MakeRecipe.MakeRecipeActivity
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.R

class SubwaySelectionItemViewModel(
        val subway: FilterItem,
        val activty: MakeRecipeActivity,
        val subwaySelectionContract: SubwaySelectionContract
) {

    val subwayCalories: String

    init {
        subwayCalories = subway.calories.toString() + " Kcal"
    }

    fun subwayInfoOnClick(view: View) {
        subwaySelectionContract.showSubwayInfo(subway)
    }

    fun selectSubwayOnClick(view: View) {
        view.setBackgroundResource(R.drawable.filter_item_background_2)
        SubwayOnProcess.sandwich.set(subway.name)
        SubwayOnProcess.setCurrentProccess(1)
    }
}
