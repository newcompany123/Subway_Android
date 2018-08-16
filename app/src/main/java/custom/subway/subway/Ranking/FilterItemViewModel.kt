package custom.subway.subway.Ranking

import android.content.Context
import android.databinding.ObservableField
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.Utility.Constants

class FilterItemViewModel(
        val filterItem: FilterItem,
        val context: Context
) {

    val filterItemIsChecked: ObservableField<Boolean> = ObservableField()

    init {
        filterItemIsChecked.set(false)
        Constants.observableSubwayFilter.addFilter(filterItem.name)
    }

    fun filterItemOnClick(view: View) {
        val parent = view as LinearLayout
        val item = parent.getChildAt(1) as TextView
        Constants.observableSubwayFilter.checkSelted(item.text.toString())
        filterItemIsChecked.set(!filterItemIsChecked.get()!!)
    }


}