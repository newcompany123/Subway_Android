package custom.subway.subway.MakeRecipe.VegetableSelection

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.R

class VegetableSelectionItemViewModel(
        val vegetable: IngredientItem
) {

    init {

    }

    fun vegetableSelcetionWithAmoutOnClick(view: View, amount: Int) {
        SubwayOnProcess.addOrUpdateVegetable(vegetable.name, amount)
        val parent = view.parent as LinearLayout
        for (i in 0 until parent.childCount) {
            val child: TextView = parent.getChildAt(i) as TextView
            if (i != amount) {
                child.setBackgroundResource(0)
                child.setTextColor(Color.parseColor("#717171"))
            } else {
                child.setBackgroundResource(R.drawable.filter_item_background_2)
                child.setTextColor(Color.parseColor("#ffffff"))
            }
        }
    }
}