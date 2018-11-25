package custom.subway.subway.MakeRecipe.SauceSelection

import android.view.View
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SauceSelectionViewModel(
    val sauceSelectionContract: SauceSelectionContract
) {
    var sauceList: IngredientResult? = null

    init {
        getSauceList()
    }

    fun getSauceList() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
            .getAPIService()
            .reqeustSauceList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    sauceList = it
                    sauceList?.let {
                        sauceSelectionContract.showSauceList(it)
                    }
                },
                onError = {
                }
            )
    }

    fun sauceSelectionIsFinishedOnclick(view: View) {
//        sauceSelectionContract.showMakeReceipeConfirmDialog()
        SubwayOnProcess.setCurrentProccess(7)

    }

    fun letsGoToMakeName(){
        SubwayOnProcess.setCurrentProccess(7)
    }
}