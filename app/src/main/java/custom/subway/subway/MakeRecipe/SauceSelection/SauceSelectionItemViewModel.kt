package custom.subway.subway.MakeRecipe.SauceSelection

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.R

class SauceSelectionItemViewModel(
        val sauce: IngredientItem
) {

    fun sauceSelectedOnClick(parent: View) {
        val childTextVIew = ((parent as LinearLayout).getChildAt(1) as TextView)
        val childText = childTextVIew.text.toString()

        when (SubwayOnProcess.addOrRemoveSauce(childText)) {
            true -> {
                childTextVIew.setTextColor(Color.parseColor("#ffffff"))
                parent.setBackgroundResource(R.drawable.filter_item_background_2)
            }
            false -> {
                childTextVIew.setTextColor(Color.parseColor("#4a4a4a"))
                parent.setBackgroundResource(0)
            }
        }
    }

}