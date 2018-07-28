package custom.subway.subway.Ranking

import custom.subway.subway.Model.SubwayList

interface RankingContract {

    fun showRanking(subwayList: SubwayList)

    fun showRecentSerachList(searchWordList: ArrayList<String>)

}