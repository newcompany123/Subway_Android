package custom.subway.subway.Ranking

import custom.subway.subway.Model.FilterList
import custom.subway.subway.Model.SubwayList

interface RankingContract {

    fun showRanking(subwayList: SubwayList)

    fun showRecentSerachList(searchWordList: ArrayList<String>?)

    fun reDrawSearchWord()

    fun showRankingBaseOnSearchword(searchWord: String)

    fun showFilter(filterList: FilterList)
}