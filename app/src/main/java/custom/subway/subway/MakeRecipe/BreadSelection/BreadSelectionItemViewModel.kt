package custom.subway.subway.MakeRecipe.BreadSelection

import android.view.View
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem

class BreadSelectionItemViewModel(
    val bread: IngredientItem
) {

    val breadCalories: String

    init {
        breadCalories = bread.calories.toString() + " Kcal"

    }

    fun selectBreadOnClick(view: View) {
        SubwayOnProcess.bread.set(bread.name)
        SubwayOnProcess.setCurrentProccess(2)
    }
}