package custom.subway.subway.API_Client


import custom.subway.subway.Login.LoginViewModel
import custom.subway.subway.Model.SubwayList
import custom.subway.subway.Model.User
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*



interface Service {

//    @FormUrlEncoded
//    @POST("/user/facebook-login/")
//    fun registService(@Field("access_token") access_token: String): Observable<User>

    @POST("/user/facebook-login/")
    fun registService(@Body access_token: LoginViewModel.test): Observable<User>


    @FormUrlEncoded
    @POST("/user/kakao-login/")
    fun registServiceKakao(@Field("access_token") access_token: String): Observable<User>

    @GET("/recipe/")
    fun getAllProductList(): Observable<SubwayList>


    @GET("/recipe/")
    fun getAllProductListBySearchWord(@Query("search") searchWrod: String): Observable<SubwayList>


    @POST("/recipe/{sandwichId}/like/")
    fun likeThisSandwich(@Path("sandwichId") sandwichId: String): Observable<ResponseBody>

}