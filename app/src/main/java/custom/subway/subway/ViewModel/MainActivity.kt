package custom.subway.subway.ViewModel

import android.os.Bundle
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Model.User
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val abc = User(this@MainActivity)
        abc.token = "adfdsf"



        abccc.setOnClickListener {
            APIClient(application = subwayApplication)
                    .getAPIService()
                    .testt()
                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onNext = {

                            },
                            onError = {

                            }

                    )

        }

    }


}
