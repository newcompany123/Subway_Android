package custom.subway.subway.view.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.databinding.library.baseAdapters.BR
import com.kakao.auth.Session
import custom.subway.subway.R
import custom.subway.subway.databinding.ActivityLoginBinding
import custom.subway.subway.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val login_viewmodel = LoginViewModel(this@LoginActivity)
        InitDataBinding(login_viewmodel)
        if (Session.getCurrentSession().isOpened) login_viewmodel.requestKakaoMyInfo()
    }

    fun InitDataBinding(login_viewmodel: LoginViewModel) {
        val loginBinding: ActivityLoginBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginBinding.setVariable(BR.LoginViewModel, login_viewmodel)
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
}
