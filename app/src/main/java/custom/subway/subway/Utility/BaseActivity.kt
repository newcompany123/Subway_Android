package custom.subway.subway.Utility

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    lateinit protected var subwayApplication: SubwayApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subwayApplication = application as SubwayApplication
    }
}