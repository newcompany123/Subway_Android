package custom.subway.subway.viewmodel

import android.app.Activity
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.AuthService
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.auth.network.response.AccessTokenInfoResponse
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import custom.subway.subway.view.ui.LoginActivity


/**
 *  Kakao with Facebook Api logic
 */
class LoginViewModel(var activity: Activity) : BaseObservable() {
    val TextView_nickname: ObservableField<String> by lazy { ObservableField<String>() }

    fun CheckKakoSession() {
        if (Session.getCurrentSession().isOpened) requestKakaoMyInfo()
        else {
            Session.getCurrentSession().addCallback(SessionCallback(requestKakaoMyInfo()))
            Session.getCurrentSession().checkAndImplicitOpen()
        }
    }

    fun KaKaoLogOut(view: View) {
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {
                TextView_nickname.set("로그아웃 성공")
            }

        })
    }

    fun requestAccessTokenInfo() {
        AuthService.getInstance().requestAccessTokenInfo(object : ApiResponseCallback<AccessTokenInfoResponse>() {
            override fun onSuccess(result: AccessTokenInfoResponse?) {

            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNotSignedUp() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun registerSubwayService(result: AccessTokenInfoResponse) {
    }

    fun requestKakaoMyInfo() {
        UserManagement.getInstance().requestMe(object : MeResponseCallback() {
            override fun onSuccess(result: UserProfile?) {
                Log.e("user info : ", result.toString())
                Log.e("user nickname : ", result!!.nickname)
                TextView_nickname.set(result.nickname)
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                CheckKakoSession()
            }

            override fun onNotSignedUp() {
                redirectLoginActivity()
            }
        })
    }

    private fun redirectLoginActivity() {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }

    class SessionCallback(var unit: Unit?) : ISessionCallback {

        override fun onSessionOpened() {
            if (null != unit) unit
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null) Log.e("Kakao login Fail -> ", exception.message.toString())
        }
    }
}