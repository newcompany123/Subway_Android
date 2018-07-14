package custom.subway.subway.Model

import java.io.Serializable


data class SubwayList(
        val count: Int,
        val next: String,
        val previous: String,
        val results: ArrayList<Subway>
)

data class Subway(
        val id: Int,
        val name: Name,
        val sandwich: Sandwich,
        val cheese: cheese,
        val vegetables: ArrayList<Vegetable>?,
        val toppings: ArrayList<Topping>,
        val sauces: ArrayList<Sauces>,
        val toasting: Boolean,
        val inventor: Inventor,
        var auth_user_like_state: Boolean,
        var auth_user_bookmark_state: Boolean,
        var like_count: Int,
        var bookmark_count: Int,
        var like_bookmark_count: Int
) : Serializable


data class Name(
        val id: Int,
        val name: String
) : Serializable

data class Inventor(
        val id: Int,
        val username: String,
        val eamil: String,
        val like_bookmartk_count: Int,
        val like_count: Int
)

data class Sandwich(
        val id: Int,
        val name: String,
        val image_left: String,
        val image_right: String,
        val image3x_left: String,
        val image3x_right: String,
        val main_ingredient: ArrayList<MainIngredient>
)

data class MainIngredient(
        val id: Int,
        val name: String,
        val image: String,
        val image3x: String,
        val quantity: String
)

data class Bread(
        val id: Int,
        val name: String,
        val image: String,
        val image3x: String
)

data class cheese(
        val id: Int,
        val name: String,
        val image: String,
        val image3x: String
)

data class Vegetable(
        val id: Int,
        val name: String,
        val quantity: String,
        val image: String,
        val image3x: String
)

data class Topping(
        val id: Int,
        val name: String,
        val image: String,
        val image3x: String
)

data class Sauces(
        val id: Int,
        val name: String,
        val image: String,
        val image3x: String
)