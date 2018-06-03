package custom.subway.subway.viewmodel

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
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Contract.LoginContract
import custom.subway.subway.Model.User
import custom.subway.subway.Utility.SubwayApplication
import custom.subway.subway.view.ui.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


/**
 *  Kakao with Facebook Api logic
 */
class LoginViewModel(val activity: Activity, val context: Context, val subwayApplication: SubwayApplication) : BaseObservable() {
    val TextView_nickname: ObservableField<String> by lazy { ObservableField<String>() }


    lateinit var callbackManager: CallbackManager
    val loginContract: LoginContract = context as LoginContract

    init {
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(context)
        callbackManager = CallbackManager.Factory.create()
    }

    fun initiateFacebookLogin(view: View) {
        val loginButton: LoginButton = view as LoginButton
        loginButton.setReadPermissions("email")

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("testt", "onSuccess 1")
            }

            override fun onCancel() {
                loginContract.facebookLoginIsFailed()
            }

            override fun onError(exception: FacebookException) {
                loginContract.facebookLoginIsFailed()
            }
        })

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("testt", "onSuccess")
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
                                    Log.d("testt", "tokenFromServer : " + it.tokenFromServer)
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
        if (Session.getCurrentSession().isOpened) requestMe()
        else {
            Session.getCurrentSession().addCallback(SessionCallback(requestMe()))
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

    fun requestMe() {
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