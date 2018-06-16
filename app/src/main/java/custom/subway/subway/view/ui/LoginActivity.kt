package custom.subway.subway.view.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.kakao.auth.Session
import custom.subway.subway.Contract.LoginContract
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import custom.subway.subway.databinding.LoginActivityBinding
import custom.subway.subway.viewmodel.LoginViewModel


class LoginActivity : BaseActivity(), LoginContract {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
    }

    private fun initDataBinding() {
        loginViewModel = LoginViewModel(this@LoginActivity, this, subwayApplication)
        val binding: LoginActivityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.loginViewModel = loginViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(LoginViewModel.SessionCallback(null))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) return
        Log.d("testt", "onActivityResult")
        loginViewModel.callbackManager.onActivityResult(requestCode, resultCode, data)
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
