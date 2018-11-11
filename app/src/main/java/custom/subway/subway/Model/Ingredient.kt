package custom.subway.subway.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class IngredientResult(
        val count: Int,
        val next: String?,
        val previous: String?,
        val results: ArrayList<IngredientItem>
) : Serializable

data class IngredientItem(
        val name: String,
        @SerializedName("image3x")
        val image: String,
        val serving_size: Int,
        val calories: Int,
        val protein: Int,
        val saturated_fat: Int,
        val main_ingredient: ArrayList<MainIngredient>,
        val ordering_num: Int,
        val sugars: Int,
        val sodium: Int
) : Serializable

