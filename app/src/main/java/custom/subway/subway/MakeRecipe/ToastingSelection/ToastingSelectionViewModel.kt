package custom.subway.subway.MakeRecipe.ToastingSelection

import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ToastingSelectionViewModel(
        val toastingSelectionContract: ToastingSelectionContract
) {
    var ToastingList: IngredientResult? = null


    init {
        getBreadList()
    }

    fun getBreadList() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .reqeustToasingList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            ToastingList = it
                            ToastingList?.let {
                                toastingSelectionContract.showToastingList(it)
                            }
                        },
                        onError = {
                        }
                )
    }
}