package custom.subway.subway.Ranking

import android.util.Log
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class RankingViewModel(
        val rankingContract: RankingContract
) {

    init {
        Log.d("order", "1")
        requestSubwayList()
    }

    fun requestSubwayList() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .getAllProductList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            rankingContract.showRanking(it)
                            Log.d("testt",""+it.results.get(2).vegetables!!.get(1)!!.name)
                        },
                        onError = {
                        }
                )
    }


}