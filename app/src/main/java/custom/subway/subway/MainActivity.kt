package custom.subway.subway

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Utility.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val abc = User(this@MainActivity)
        abc.token = "adfdsf"



        abccc.setOnClickListener {


            APIClient()
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
