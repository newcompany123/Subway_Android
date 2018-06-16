package custom.subway.subway.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
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
import custom.subway.subway.Contract.LoginContract
import custom.subway.subway.Model.User
import custom.subway.subway.R
import custom.subway.subway.Utility.SubwayApplication
import custom.subway.subway.view.ui.LoginActivity
import custom.subway.subway.view.ui.RankingActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor

class LoginViewModel(val activity: Activity, val context: Context, val subwayApplication: SubwayApplication) : BaseObservable() {
//    val TextView_nickname: ObservableField<String> by lazy { ObservableField<String>() }

    var callbackManager: CallbackManager
    val loginContract: LoginContract = context as LoginContract
    val realFacebookLoginBtn: com.facebook.login.widget.LoginButton by lazy {
        activity.findViewById<com.facebook.login.widget.LoginButton>(R.id.button_kakako_login)
    }

    init {
//        CheckKakoSession()

        checkUserAlreadyLogined()

        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(context)
        callbackManager = CallbackManager.Factory.create()
    }

    private fun checkUserAlreadyLogined() {
        User.getInstance().checkLogin()
    }

    fun initiateFacebookLogin(view: View) {
        realFacebookLoginBtn.performClick()
        realFacebookLoginBtn.setReadPermissions("email")

        // Callback registration
        realFacebookLoginBtn.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("testt", "onSuccess 1")
            }

            override fun onCancel() {
                Log.d("testt", "onCancel 1")
                loginContract.facebookLoginIsFailed()
            }

            override fun onError(exception: FacebookException) {
                Log.d("testt", "onError 1")
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
                        Log.d("testt", "onCancel")
                        loginContract.facebookLoginIsFailed()
                    }

                    override fun onError(exception: FacebookException) {
                        Log.d("testt", "onError : "+ exception.message)
                        loginContract.facebookLoginIsFailed()
                    }
                })
    }

    fun registerSubwayService(loginResult: LoginResult? = null, loginResultKakao: AccessTokenInfoResponse? = null) {
        if (null == loginResultKakao) {
            loginResult!!.accessToken?.token?.let {
                Log.d("testt", "FACEBOOK TOKEN : " + it)
                APIClient(application = subwayApplication)
                        .getAPIService()
                        .registService(it.toString())
                        .subscribeOn(Schedulers.single())
                        .distinct()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = { createdUser ->
                                    createdUser?.token?.let {
                                        User.getInstance().token = it
                                        moveToRankingActvity()
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
        } else {
            loginResultKakao.userId.let {
                APIClient(application = subwayApplication)
                        .getAPIService()
                        .registServiceKakao(it.toString())
                        .subscribeOn(Schedulers.single())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { success ->
                                    Log.e("Kakao 성공", "성공")
                                },
                                { fail ->
                                    Log.e("Kakao 패일", fail.message)
                                }
                        )
            }
        }
    }

    fun CheckKakoSession() {
        if (Session.getCurrentSession().isOpened) RequestMeKakao()
        else {
            Session.getCurrentSession().addCallback(SessionCallback(RequestMeKakao()))
            Session.getCurrentSession().checkAndImplicitOpen()
        }
    }

    fun kalogout() {
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {
//                TextView_nickname.set("로그아웃 성공")
            }
        })
    }

    fun RequestMeKakao() {
        UserManagement.getInstance().requestMe(object : MeResponseCallback() {
            override fun onSuccess(result: UserProfile?) {
                Log.e("user info : ", result.toString())
                Log.e("user nickname : ", result!!.nickname)
//                TextView_nickname.set(result.nickname)
                requestAccessTokenInfo()
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Log.e("errorResult : ", errorResult.toString())
                CheckKakoSession()
            }

            override fun onNotSignedUp() {
                Log.e("onNotSignedUp : ", "onNotSignedUp")
                redirectLoginActivity()
            }
        })
    }

    fun requestAccessTokenInfo() {
        AuthService.getInstance().requestAccessTokenInfo(object : ApiResponseCallback<AccessTokenInfoResponse>() {
            override fun onSuccess(result: AccessTokenInfoResponse?) {
                val userId: Long = result!!.userId
                val expiresInMilis: Long = result.expiresInMillis
                Log.e("userId: ", "" + userId)
                Log.e("expiresInMilis: ", expiresInMilis.toString())
                registerSubwayService(null, result)
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                requestAccessTokenInfo()
            }

            override fun onNotSignedUp() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    fun moveToRankingActvityOnclick(view: View) {
        moveToRankingActvity()
    }

    val moveToRankingActvity: () -> (Unit) = {
        context.startActivity(context.intentFor<RankingActivity>())
    }


}