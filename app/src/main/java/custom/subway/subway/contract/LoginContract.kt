package custom.subway.subway.contract

import com.kakao.usermgmt.response.model.UserProfile

/**
 * Created by Mingyengun on 2018-05-27.
 */
interface LoginContract {
    fun ResultKaKaoLogin(user: UserProfile? =null)
}