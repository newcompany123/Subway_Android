package custom.subway.subway.MakeRecipe.SauceSelection

import custom.subway.subway.Model.IngredientResult

interface SauceSelectionContract {

    fun showSauceList(sauceList: IngredientResult)
    fun showMakeReceipeConfirmDialog()

}