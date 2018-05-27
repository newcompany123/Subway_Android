package custom.subway.subway.Utility

import android.content.Context
import custom.subway.subway.Constants


class User(val context: Context) {

    var isLogin: Boolean = false

    var token: String = ""
        set(userNewToken) {
            field = userNewToken
            with(context.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE).edit()) {
                putString(Constants.TOKEN_KEY, userNewToken).commit()
            }
            isLogin()
        }

    fun isLogin() {
        with(context.getSharedPreferences(Constants.USER_TOKEN_PREF, Context.MODE_PRIVATE).getString(Constants.TOKEN_KEY, Constants.EMPTY_TOKEN)) {
            if (this == Constants.EMPTY_TOKEN) isLogin = false else isLogin = true
        }
    }


}