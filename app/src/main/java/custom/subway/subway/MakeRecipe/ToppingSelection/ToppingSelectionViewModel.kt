package custom.subway.subway.MakeRecipe.ToppingSelection

import android.view.View
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ToppingSelectionViewModel(
        val toppingSelectionContract: ToppingSelectionContract
) {
    var toppingList: IngredientResult? = null

    init {
        getToppingList()
    }


    fun getToppingList() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .reqeustToppingList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            toppingList = it
                            toppingList?.let {
                                toppingSelectionContract.showToppingList(it)
                            }
                        },
                        onError = {
                        }
                )
    }

    fun toppingSelectionIsFinishedOnclick(view: View) {
        SubwayOnProcess.setCurrentProccess(3)
    }

}