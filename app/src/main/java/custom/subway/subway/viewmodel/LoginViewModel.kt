package custom.subway.subway.ViewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
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
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Model.User
import custom.subway.subway.R
import custom.subway.subway.Utility.SubwayApplication
import custom.subway.subway.contract.LoginContract
import custom.subway.subway.view.ui.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


/**
 *  Kakao with Facebook Api logic
 */
class LoginViewModel(private val activity: Activity
                     , private val context: Context
                     , private val subwayApplication: SubwayApplication)
    : BaseObservable() {

    val TextView_nickname: ObservableField<String> by lazy { ObservableField<String>() }

    lateinit var callbackManager: CallbackManager
    val loginContract: LoginContract = context as LoginContract

//    init {
//        Log.e("111", "111")
//        initiateFacebookLogin()
//    }

    fun initiateFacebookLogin() {
        Log.e("222", "222")
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(context)

        callbackManager = CallbackManager.Factory.create()

        val loginButton = activity.findViewById<View>(R.id.login_button) as LoginButton
        loginButton.setReadPermissions("email")
        Log.e("333", "333")
        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.e("testt", "onSuccess 1")
            }

            override fun onCancel() {
                Log.e("testt", "onSuccess 1")
                loginContract.facebookLoginIsFailed()
            }

            override fun onError(exception: FacebookException) {
                Log.e("testt", "onSuccess 1")
                loginContract.facebookLoginIsFailed()
            }
        })

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.e("testt", "onSuccess")
                        registerSubwayService(loginResult)
                    }

                    override fun onCancel() {
                        loginContract.facebookLoginIsFailed()
                    }

                    override fun onError(exception: FacebookException) {
                        loginContract.facebookLoginIsFailed()
                    }
                })
    }

    fun registerSubwayService(loginResult: LoginResult) {
        loginResult.accessToken?.let {
            APIClient(application = subwayApplication)
                    .getAPIService()
                    .registService(it.toString())
                    .subscribeOn(Schedulers.single())
                    .distinct()
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onNext = {
                                with(User(context)) {
                                    this.token = it.tokenFromServer
                                    Log.e("testt", "tokenFromServer : " + it.tokenFromServer)
                                }
                            },
                            onError = {
                                loginContract.facebookLoginIsFailed()
                            },
                            onComplete = {
                                loginContract.facebookLoginIsCompleted()
                            }
                    )
        }
    }

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