package custom.subway.subway.API_Client

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseAPIResponse(
        @SerializedName("error_code")
        val errorCode: Int,
        @SerializedName("error_message")
        val errorMessage: String) : Serializable