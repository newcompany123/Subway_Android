package custom.subway.subway.Login

import android.app.Activity
import android.content.Context
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
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Model.User
import custom.subway.subway.R
import custom.subway.subway.Ranking.RankingActivity
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import org.jetbrains.anko.intentFor

class LoginViewModel(val activity: Activity, val context: Context, val subwayApplication: SubwayApplication) : BaseObservable() {

    lateinit var callbackManager: CallbackManager
    val loginContract: LoginContract = context as LoginContract
    val realFacebookLoginBtn: com.facebook.login.widget.LoginButton by lazy {
        activity.findViewById<com.facebook.login.widget.LoginButton>(R.id.button_kakako_login)
    }

    init {
        if (subwayApplication.isLogin) moveToRankingActvity()
        else {
            FacebookSdk.sdkInitialize(getApplicationContext())
            AppEventsLogger.activateApp(context)
            callbackManager = CallbackManager.Factory.create()
        }
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
                        Log.d("testt", "onError : " + exception.message)
                        loginContract.facebookLoginIsFailed()
                    }
                })
    }

    class test(val access_token: String) {

    }


    private fun registerSubwayService(
            loginResult: LoginResult? = null
    ) {


        loginResult!!.accessToken?.token?.let {
            Log.d("testt", "FACEBOOK TOKEN : " + it)
//            val jsonObject = JsonObject()
//            jsonObject.addProperty("access_token", it)
//            val requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString())
//

//            val somevalue = "somevalue"
            val body = RequestBody.create(MediaType.parse("multipart/form-data"), it)


            APIClient(application = subwayApplication)
                    .getAPIService()
                    .registService(test(it.toString()))
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
    }

    fun moveToRankingActvityOnclick(view: View) {
        moveToRankingActvity()
    }

    private fun moveToRankingActvity() {
        context.startActivity(context.intentFor<RankingActivity>())
    }


}