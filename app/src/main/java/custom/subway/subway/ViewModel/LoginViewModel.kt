package custom.subway.subway.ViewModel

import android.app.Activity
import android.content.Context
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
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Contract.LoginContract
import custom.subway.subway.Model.User
import custom.subway.subway.R
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LoginViewModel(val activity: Activity, val context: Context, val subwayApplication: SubwayApplication) {

    lateinit var callbackManager: CallbackManager
    val loginContract: LoginContract = context as LoginContract

    init {
        initiateFacebookLogin()
    }

    fun initiateFacebookLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(context)

        callbackManager = CallbackManager.Factory.create()

        val loginButton = activity.findViewById<View>(R.id.login_button) as LoginButton
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
                                    Log.d("testt", "tokenFromServer : "+it.tokenFromServer)
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

}