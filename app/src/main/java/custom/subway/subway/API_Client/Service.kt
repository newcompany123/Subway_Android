package custom.subway.subway.API_Client


import custom.subway.subway.Login.User2
import custom.subway.subway.Model.FilterList
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.Model.SubwayList
import custom.subway.subway.Model.User
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*


interface Service {

    @FormUrlEncoded
    @POST("/user/facebook-login/")
    fun registService(@Field("access_token") access_token: String): Observable<User2>
//
//    @POST("/user/facebook-login/")
//    fun registService(@Body access_token: LoginViewModel.test): Observable<User>


    @POST("/user/facebook-login/")
    fun registService()
            : Observable<User>


    @FormUrlEncoded
    @POST("/user/kakao-login/")
    fun registServiceKakao(@Field("access_token") access_token: String): Observable<User>

    @GET("/recipe/")
    fun getAllProductList(): Observable<SubwayList>


    @GET("/recipe/")
    fun getAllProductListBySearchWord(@Query("search") searchWrod: String): Observable<SubwayList>


    @POST("/recipe/{sandwichId}/like/")
    fun likeThisSandwich(@Path("sandwichId") sandwichId: String): Observable<ResponseBody>


    @GET("/recipe/")
    fun requestRankingBasedOnFilter(
            @Query("ordering") ordering: String,
            @Query("sandwich") sandwichId: String
    ): Observable<SubwayList>


    @GET("/ingredients/sandwich/")
    fun requestSubwayList(): Observable<FilterList>

    @GET("/ingredients/bread/")
    fun reqeustBreadList(): Observable<IngredientResult>

    @GET("/ingredients/toppings/")
    fun reqeustToppingList(): Observable<IngredientResult>

    @GET("/ingredients/cheese/")
    fun reqeustCheeseList(): Observable<IngredientResult>

    @GET("/ingredients/toasting/")
    fun reqeustToasingList(): Observable<IngredientResult>

    @GET("/ingredients/vegetables/")
    fun reqeustVegetableList(): Observable<IngredientResult>

    @GET("/ingredients/sauces/")
    fun reqeustSauceList(): Observable<IngredientResult>

}