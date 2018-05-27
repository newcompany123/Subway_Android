package custom.subway.subway.ViewModel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import custom.subway.subway.Utility.SubwayApplication

class BaseActivity : AppCompatActivity() {

    lateinit protected var subwayApplication: SubwayApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        subwayApplication = application as SubwayApplication
    }


}