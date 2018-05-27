package custom.subway.subway.API_Client

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface APIService {

    @GET("")
    fun testt(): Observable<ResponseBody>

}