package custom.subway.subway.Model

import com.google.gson.annotations.SerializedName

data class FilterList(
        val count: Int,
        val next: String,
        val previous: String,
        val results: ArrayList<FilterItem>
)

data class FilterItem(
        val name: String,
        @SerializedName("image_left")
        val image: String
)