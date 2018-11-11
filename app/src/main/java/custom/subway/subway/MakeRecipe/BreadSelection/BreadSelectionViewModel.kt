package custom.subway.subway.MakeRecipe.BreadSelection

import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class BreadSelectionViewModel(
       val  breadSelctionContract: BreadSelctionContract
) {

    var breadList: IngredientResult? = null

    init {
        getBreadList()
    }

    fun getBreadList() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .reqeustBreadList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            breadList = it
                            breadList?.let {
                                breadSelctionContract.showBreadSelectionList(it)
                            }
                        },
                        onError = {
                        }
                )
    }
}