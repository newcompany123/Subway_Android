package custom.subway.subway.MakeRecipe.ToppingSelection

import custom.subway.subway.Model.IngredientResult

interface ToppingSelectionContract {

    fun showToppingList(toppingList : IngredientResult)
}