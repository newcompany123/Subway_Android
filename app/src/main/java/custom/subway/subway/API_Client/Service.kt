package custom.subway.subway.API_Client

import com.kakao.usermgmt.response.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface Service {
    @FormUrlEncoded
    @POST("user/kakao-login/")
    fun registServiceKakao(@Field("access_token") access_token: String): Observable<User>
}