package custom.subway.subway.Model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.annotations.SerializedName
import custom.subway.subway.Utility.Constants
import java.io.Serializable


open class User(val context: Context) : Serializable {

    @SerializedName("token")
    var tokenFromServer: String = ""
    var tokenSharedPreferences: SharedPreferences = context.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE)

    @SerializedName("registered_token")
    var token: String = ""
        set(userNewToken) {
            field = userNewToken
            with(tokenSharedPreferences.edit()) {
                putString(Constants.TOKEN_KEY, userNewToken).commit()
            }
            checkLogin() // 토큰이 설정 되면 로그인 상태 변경해준다
        }

    fun checkLogin(): Boolean {
        with(context.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE).getString(Constants.TOKEN_KEY, Constants.EMPTY_TOKEN)) {
            if (this == Constants.EMPTY_TOKEN) return false else return true
        }
        return false
    }
}