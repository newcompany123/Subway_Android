package custom.subway.subway.View

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import custom.subway.subway.Contract.LoginContract
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import custom.subway.subway.ViewModel.LoginViewModel
import custom.subway.subway.databinding.LoginActivityBinding


class LoginActivity : BaseActivity(), LoginContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: LoginActivityBinding = DataBindingUtil.setContentView<LoginActivityBinding>(this, R.layout.login_activity)
        val loginViewModel = LoginViewModel(this, this, subwayApplication)
        binding.loginModel = loginViewModel
    }

    override fun moveMainActivity() {
        super.moveMainActivity()
    }


    override fun facebookLoginIsCompleted() {
        Log.d("testt", "facebookLoginIsCompleted")
    }

    override fun facebookLoginIsFailed() {
        Log.d("testt", "facebookLoginIsFailed")
    }
}
