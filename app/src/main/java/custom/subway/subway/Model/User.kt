package custom.subway.subway.Model

import android.content.Context
import android.util.Log
import com.google.gson.annotations.SerializedName
import custom.subway.subway.Utility.Constants
import custom.subway.subway.Utility.SubwayApplication.Companion.context
import io.reactivex.subjects.PublishSubject
import java.io.Serializable


open class User() : Serializable {


    val loginCheckPublishSubject = PublishSubject.create<Boolean>()

    companion object {
        private val userInstance: User by lazy { User() }
        fun getInstance(): User = userInstance
    }

    @SerializedName("token")
    var token: String = ""
        set(userNewToken) {
            Log.d("testt", "SETTER : " + userNewToken)
            field = userNewToken
            with(context?.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE)!!.edit()) {
                Log.d("testt", "SETTER 22")
                putString(Constants.TOKEN_KEY, userNewToken).commit()
                checkLogin()
            }
        }
        get() {
            return context?.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE)?.getString(Constants.TOKEN_KEY, Constants.EMPTY_TOKEN)!!
        }

    fun checkLogin() {
        Log.d("testt", "checkLogin")
        context?.let {
            with(it.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE).getString(Constants.TOKEN_KEY, Constants.EMPTY_TOKEN)) {
                if (this == Constants.EMPTY_TOKEN) return loginCheckPublishSubject.onNext(false)
                else return loginCheckPublishSubject.onNext(true)
            }
        }
        loginCheckPublishSubject.onNext(false)
    }
}