package custom.subway.subway.view.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.kakao.auth.Session
import custom.subway.subway.BR
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import custom.subway.subway.ViewModel.LoginViewModel
import custom.subway.subway.contract.LoginContract
import custom.subway.subway.databinding.LoginActivityBinding


class LoginActivity : BaseActivity(), LoginContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * need to add Kakao and Facebook logic!
         */
//        val binding: LoginActivityBinding = DataBindingUtil.setContentView<LoginActivityBinding>(this, R.layout.login_activity)
//        val loginViewModel = LoginViewModel(this, this, subwayApplication)
//        binding.loginModel = loginViewModel

        val loginViewModel = LoginViewModel(this@LoginActivity, this, subwayApplication)
        InitDataBinding(loginViewModel)
        if (Session.getCurrentSession().isOpened) loginViewModel.requestKakaoMyInfo()

    }

    private fun InitDataBinding(loginViewModel: LoginViewModel) {
        val loginBinding: LoginActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.login_activity)
        loginBinding.setVariable(BR.loginViewModel, loginViewModel)
        loginBinding.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(LoginViewModel.SessionCallback(null))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) return
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun moveMainActivity() {
        super.moveMainActivity()
    }

    override fun facebookLoginIsCompleted() {
        Log.e("testt", "facebookLoginIsCompleted")
    }

    override fun facebookLoginIsFailed() {
        Log.e("testt", "facebookLoginIsFailed")
    }
}
