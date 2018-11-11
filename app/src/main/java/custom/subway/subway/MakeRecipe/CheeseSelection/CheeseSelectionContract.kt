package custom.subway.subway.MakeRecipe.CheeseSelection

import custom.subway.subway.Model.IngredientResult

interface CheeseSelectionContract {
    fun showCheeseSelectionList(cheeseList: IngredientResult)
}