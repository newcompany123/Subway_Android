package custom.subway.subway.Ranking

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import custom.subway.subway.Model.SubwayList
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import custom.subway.subway.databinding.ActivityRankingBinding
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent


class RankingActivity : BaseActivity(), RankingContract {


    lateinit var rankingViewModel: RankingViewModel
    lateinit var rankingListAdapter: RankingListAdapter
    lateinit var serachwordListAdapter: SearchWordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        abc()
    }


    fun initDataBinding() {
        rankingViewModel = RankingViewModel(this)
        val binding: ActivityRankingBinding = DataBindingUtil.setContentView(this, R.layout.activity_ranking)
        binding.rankingViewModel = rankingViewModel
    }


    override fun showRanking(subwayList: SubwayList) {
        val rankingListRecyclerView = findViewById<RecyclerView>(R.id.rankingList)
        rankingListRecyclerView.layoutManager = LinearLayoutManager(this)
        rankingListAdapter = RankingListAdapter(
                context = this,
                subwayList = subwayList
        )
        rankingListRecyclerView.adapter = rankingListAdapter
    }


    override fun showRecentSerachList(searchWordList: ArrayList<String>) {
        val serachBoxRecyclerView = findViewById<RecyclerView>(R.id.serachWordList)
        serachBoxRecyclerView.layoutManager = LinearLayoutManager(this)
        serachwordListAdapter = SearchWordListAdapter(
                context = this,
                searchWordList = searchWordList.reversed() as ArrayList<String>
        )
        serachBoxRecyclerView.adapter = serachwordListAdapter
    }

    fun abc() {
        KeyboardVisibilityEvent.setEventListener(
                this@RankingActivity
        ) {
            rankingViewModel.searchBoxVisibility.set(it)
            if (it) rankingViewModel.requestRecentWordList()
        }
    }


}
