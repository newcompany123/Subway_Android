package custom.subway.subway.MakeRecipe.SubwaySelection

import android.databinding.ObservableField
import android.util.Log
import android.view.View
import android.widget.TextView
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.Model.FilterList
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SubwaySelectionViewModel(
        val subwaySelectionContract: SubwaySelectionContract
) {

    var subwayList: FilterList? = null
    var selectedSubwayFilter: ObservableField<String> = ObservableField()

    init {
        Log.d("ttt", "Subway INIT")
        getDefaultSubwayList()
        selectedSubwayFilter.set("모두")
    }


    fun getDefaultSubwayList() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .requestSubwayList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            subwayList = it
                            subwayList?.let {
                                subwaySelectionContract.showSubwaySelectionList(it)
                            }
                        },
                        onError = {
                        }
                )
    }

    fun categoryFilterOnClick(view: View) {
        val selectedFilter = (view as TextView).text.toString()
        selectedSubwayFilter.set(selectedFilter)
        if (selectedFilter == "모두") {
            subwaySelectionContract.sortedSubwaySelectionList(subwayList!!)
        } else {
            val tempList: ArrayList<FilterItem> = ArrayList()
            subwayList?.let {
                for (i in 0 until it.results.size) {
                    for (j in 0 until it.results.get(i).category.size) {
                        if (it.results.get(i).category.get(j).name == selectedFilter) {
                            tempList.add(it.results.get(i))
                        }
                    }
                }
            }

            val temp = FilterList(
                    count = subwayList!!.count,
                    next = subwayList!!.next,
                    previous = subwayList!!.previous,
                    results = tempList
            )
            subwaySelectionContract.sortedSubwaySelectionList(temp)

        }

    }
}