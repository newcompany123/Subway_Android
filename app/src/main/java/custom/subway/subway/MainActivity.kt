package custom.subway.subway

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Utility.User
import rx.subjects.PublishSubject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val abc = User(this@MainActivity)
        abc.token = "adfdsf"

        PublishSubject.create<>()


        APIClient()
                .getAPIService()
                .testt()



    }


}
