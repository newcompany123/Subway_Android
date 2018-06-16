package custom.subway.subway.view.ui

import android.os.Bundle
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import io.reactivex.schedulers.Schedulers

class RankingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)



        APIClient(application = subwayApplication)
                .getAPIService()
                .getAllProductList()



        APIClient(application = subwayApplication)
                .getAPIService()
                .registService("af")
                .subscribeOn(Schedulers.single())
                .distinct()
    }
}
