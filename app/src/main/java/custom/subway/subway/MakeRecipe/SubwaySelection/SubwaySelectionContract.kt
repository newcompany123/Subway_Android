package custom.subway.subway.MakeRecipe.SubwaySelection

import custom.subway.subway.Model.FilterItem
import custom.subway.subway.Model.FilterList

interface SubwaySelectionContract {

    fun sortedSubwaySelectionList(filterList: FilterList)

    fun showSubwayInfo(subway: FilterItem)

    fun showSubwaySelectionList(filterList: FilterList)

    fun showBreadSelectionList(filterList: FilterList)



}