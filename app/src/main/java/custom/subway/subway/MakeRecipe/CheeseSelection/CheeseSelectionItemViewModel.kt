package custom.subway.subway.MakeRecipe.CheeseSelection

import android.view.View
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.R

class CheeseSelectionItemViewModel(
        val cheese: IngredientItem
) {
    val cheeseCalories: String

    init {
        cheeseCalories = cheese.calories.toString() + " Kcal"
    }

    fun selectCheeseOnClick(view: View) {
        view.setBackgroundResource(R.drawable.filter_item_background_2)
        SubwayOnProcess.cheese.set(cheese.name)
        SubwayOnProcess.setCurrentProccess(4)
    }
}
