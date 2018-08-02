package custom.subway.subway.Ranking

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import custom.subway.subway.LocalDB.deleteRecentSearchWordFromDB

class SearchWordItemViewModel(
        val context: Context,
        val searchWord: String,
        val position: Int,
        val rankingContract: RankingContract
) {


    fun deleteSearchWord(view: View) {
        val parent = view.parent as LinearLayout
        val searchWord = (parent.getChildAt(0) as TextView).text.toString()
        Log.d("testt", "WORD : " + searchWord)
        deleteRecentSearchWordFromDB(searchWord)
        rankingContract.reDrawSearchWord()
    }

    fun searchBaseOnRecentSearchWord(view: View) {
        val searchWord = (view as TextView).text.toString()
        rankingContract.showRankingBaseOnSearchword(searchWord)
    }


}
