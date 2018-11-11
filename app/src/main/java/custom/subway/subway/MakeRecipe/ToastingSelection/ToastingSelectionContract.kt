package custom.subway.subway.MakeRecipe.ToastingSelection

import custom.subway.subway.Model.IngredientResult


interface ToastingSelectionContract{
    fun showToastingList(toastingList : IngredientResult)
}