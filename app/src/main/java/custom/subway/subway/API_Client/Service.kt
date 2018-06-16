package custom.subway.subway.API_Client


import custom.subway.subway.Model.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {

    @FormUrlEncoded
    @POST("user/facebook-login/")
    fun registService(@Field("access_token") access_token: String): Observable<User>

    @FormUrlEncoded
    @POST("user/kakao-login/")
    fun registServiceKakao(@Field("access_token") access_token: String): Observable<User>

    @GET("/product/")
    fun getAllProductList()

}