package custom.subway.subway.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilterList(
        val count: Int,
        val next: String?,
        val previous: String?,
        val results: ArrayList<FilterItem>
) : Serializable

data class FilterItem(
        val name: String,
        @SerializedName("image3x_right")
        val image: String,
        val serving_size: Int,
        val calories: Int,
        val protein: Int,
        val saturated_fat: Int,
        val main_ingredient: ArrayList<MainIngredient>,
        val ordering_num: Int,
        val sugars: Int,
        val sodium: Int,
        val category: ArrayList<Name>
) : Serializable


data class Category(
        val name: ArrayList<Name>
) : Serializable


