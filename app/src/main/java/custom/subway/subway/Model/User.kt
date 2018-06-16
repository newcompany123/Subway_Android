package custom.subway.subway.Model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.annotations.SerializedName
import custom.subway.subway.Utility.Constants
import custom.subway.subway.Utility.SubwayApplication.Companion.context
import io.reactivex.subjects.PublishSubject
import java.io.Serializable


open class User private constructor() : Serializable {


    val loginCheckPublishSubject = PublishSubject.create<Boolean>()

    companion object {
        private val userInstance: User by lazy { User() }

        fun getInstance(): User = userInstance
    }

    var tokenSharedPreferences: SharedPreferences? = context?.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE)

    @SerializedName("token")
    var token: String = ""
        set(userNewToken) {
            field = userNewToken
            tokenSharedPreferences?.let {
                with(it.edit()) {
                    putString(Constants.TOKEN_KEY, userNewToken).commit()
                }
                checkLogin() // 토큰이 설정 되면 로그인 상태 변경해준다
            }
        }

    fun checkLogin() {
        context?.let {
            with(it.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE).getString(Constants.TOKEN_KEY, Constants.EMPTY_TOKEN)) {
                if (this == Constants.EMPTY_TOKEN) return loginCheckPublishSubject.onNext(false)
                else return loginCheckPublishSubject.onNext(true)
            }
        }
        loginCheckPublishSubject.onNext(false)
    }
}