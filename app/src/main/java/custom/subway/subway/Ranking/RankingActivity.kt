package custom.subway.subway.Ranking

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import custom.subway.subway.Menu.MenuViewModel
import custom.subway.subway.Model.FilterList
import custom.subway.subway.Model.SubwayList
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import custom.subway.subway.Utility.Constants
import custom.subway.subway.Utility.dpToPx
import custom.subway.subway.databinding.ActivityRankingBinding
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent


class RankingActivity : BaseActivity(), RankingContract {


    lateinit var rankingViewModel: RankingViewModel
    lateinit var rankingListAdapter: RankingListAdapter
    lateinit var serachwordListAdapter: SearchWordListAdapter
    lateinit var filterAdapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initiateSoftkeyboardVisibilityListener()
    }


    fun initDataBinding() {
        rankingViewModel = custom.subway.subway.Ranking.RankingViewModel(this, this@RankingActivity)
        val binding: ActivityRankingBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_ranking)
        binding.let {
            it.rankingViewModel = rankingViewModel
            it.bottomMenu!!.menuViewModel = MenuViewModel.getInstance(application)
            it.filterList!!.rankingViewModel = rankingViewModel
            it.filterList!!.filter = Constants
        }
    }

    override fun showFilter(filterList: FilterList) {
        val parent = findViewById<View>(R.id.filterList)
        val filterRecyclerView = parent.findViewById<RecyclerView>(R.id.filterRecyclerView)
        filterRecyclerView.apply {
            layoutManager = GridLayoutManager(this@RankingActivity, 3)
            isNestedScrollingEnabled = false
            adapter = custom.subway.subway.Ranking.FilterAdapter(
                context = this@RankingActivity,
                filterList = filterList
            )
            val itemCount = (adapter.itemCount) / 3
            layoutParams.height = itemCount * dpToPx(110)
        }
    }

    override fun showRanking(subwayList: SubwayList) {
        val rankingListRecyclerView = findViewById<RecyclerView>(R.id.rankingList)
        rankingListRecyclerView.layoutManager = LinearLayoutManager(this)
        rankingListAdapter = custom.subway.subway.Ranking.RankingListAdapter(
            context = this,
            subwayList = subwayList
        )
        rankingListRecyclerView.adapter = rankingListAdapter
    }

    override fun showRecentSerachList(searchWordList: ArrayList<String>?) {
        val serachBoxRecyclerView = findViewById<RecyclerView>(R.id.serachWordList)
        serachBoxRecyclerView.layoutManager = LinearLayoutManager(this)
        serachwordListAdapter = custom.subway.subway.Ranking.SearchWordListAdapter(
            rankingContract = this,
            context = this,
            searchWordList = searchWordList!!.reversed()
        )
        serachBoxRecyclerView.adapter = serachwordListAdapter
    }

    override fun reDrawSearchWord() {
        rankingViewModel.requestRecentWordList()
    }

    override fun showRankingBaseOnSearchword(searchWord: String) {
        rankingViewModel.requestSubwayList(searchWord)
    }

    fun initiateSoftkeyboardVisibilityListener() {
        KeyboardVisibilityEvent.setEventListener(
                this@RankingActivity
        ) {
            rankingViewModel.searchBoxVisibility.set(it)
            if (it) rankingViewModel.requestRecentWordList()
        }
    }


}
