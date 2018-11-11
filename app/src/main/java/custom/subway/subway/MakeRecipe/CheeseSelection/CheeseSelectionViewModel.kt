package custom.subway.subway.MakeRecipe.CheeseSelection

import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CheeseSelectionViewModel(
        val cheeseSelectionContract: CheeseSelectionContract
) {
    var cheeseList: IngredientResult? = null

    init {
        getCheeseList()
    }

    fun getCheeseList() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .reqeustCheeseList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            cheeseList = it
                            cheeseList?.let {
                                cheeseSelectionContract.showCheeseSelectionList(it)
                            }
                        },
                        onError = {
                        }
                )
    }
}