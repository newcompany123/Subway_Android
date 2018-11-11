package custom.subway.subway.MakeRecipe.VegetableSelection

import android.view.View
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class VegetableSelectionViewModel(
        val vegetableSelectionContract: VegetableSelectionContract
) {
    var vegetableList: IngredientResult? = null


    init {
        getVegetabeLIst()
    }

    fun getVegetabeLIst() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .reqeustVegetableList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            vegetableList = it
                            vegetableList?.let {
                                vegetableSelectionContract.showVegetableSelectionList(it)
                            }
                        },
                        onError = {
                        }
                )
    }

    fun vegetableSelectionIsFinishedOnclick(view: View) {
        SubwayOnProcess.setCurrentProccess(6)
    }
}