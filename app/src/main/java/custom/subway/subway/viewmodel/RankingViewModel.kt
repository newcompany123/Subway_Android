package custom.subway.subway.viewmodel

import android.databinding.DataBindingUtil
import android.os.Bundle
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import custom.subway.subway.databinding.ActivityRankingBinding

class RankingViewModel : BaseActivity() {

    lateinit var rankingViewModel: RankingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
    }


    private fun initDataBinding() {
        rankingViewModel = RankingViewModel()
        val binding: ActivityRankingBinding = DataBindingUtil.setContentView(this, R.layout.activity_ranking)
        binding.rankingViewModel = rankingViewModel
    }
}