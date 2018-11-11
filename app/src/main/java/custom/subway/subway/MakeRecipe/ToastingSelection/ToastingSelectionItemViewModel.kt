package custom.subway.subway.MakeRecipe.ToastingSelection

import android.view.View
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.R

class ToastingSelectionItemViewModel(
        val toasting : IngredientItem
){

    fun selectToastingOnClick(view: View) {
        view.setBackgroundResource(R.drawable.filter_item_background_2)
        SubwayOnProcess.toasting.set(toasting.name)
        SubwayOnProcess.setCurrentProccess(5)
    }
}