package custom.subway.subway.Ranking

import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.LocalDB.SearchWordTable
import custom.subway.subway.LocalDB.deleteAllRecentSearchWordFromDB
import custom.subway.subway.LocalDB.saveRecentSerachWordToDB
import custom.subway.subway.Utility.Constants
import custom.subway.subway.Utility.SubwayApplication
import custom.subway.subway.Utility.hideSoftKeyboard
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.realm.Realm


class RankingViewModel(
        val rankingContract: RankingContract,
        val activity: RankingActivity
) {

    var searchBoxVisibility: ObservableField<Boolean> = ObservableField()
    var requestedSearchWord: ObservableField<String> = ObservableField()
    var backButtonVisibility: ObservableField<Boolean> = ObservableField()
    var filterVisibility: ObservableField<Boolean> = ObservableField()
    val startSerachListener: StartSerachListener


    init {
        requestFilterList()
        requestSubwayList(null)
        searchBoxVisibility.set(false)
        backButtonVisibility.set(false)
        filterVisibility.set(false)

        startSerachListener = StartSerachListener()
    }

    fun sortingOrderOnClick(sortingOrder : View){
        Constants.selectedSortingOrder.set((sortingOrder as TextView).text.toString())
    }

    fun categoryFilterOnClick(sortingOrder : View){
        Constants.seletedCategoryFilter.set((sortingOrder as TextView).text.toString())
    }

    fun requestFilterList() {
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .requestSubwayList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            rankingContract.showFilter(it)
                        },
                        onError = {
                        }
                )

    }

    fun requestSubwayList(searchWrod: String?) {
        when (searchWrod) {
            null -> {
                APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                        .getAPIService()
                        .getAllProductList()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {
                                    rankingContract.showRanking(it)
                                },
                                onError = {
                                }
                        )
            }
            else -> {
                backButtonVisibility.set(true)
                requestedSearchWord.set(searchWrod)
                APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                        .getAPIService()
                        .getAllProductListBySearchWord(searchWrod)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {
                                    searchBoxVisibility.set(false)
                                    rankingContract.showRanking(it)
                                    hideSoftKeyboard(activity)
                                },
                                onError = {
                                }
                        )
            }
        }
    }

    fun applySearchFilter(view: View) {
        backButtonVisibility.set(true)
        APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                .getAPIService()
                .requestRankingBasedOnFilter("", "")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            filterVisibility.set(!filterVisibility.get()!!)
                            rankingContract.showRanking(it)
                            hideSoftKeyboard(activity)
                        },
                        onError = {
                        }
                )
    }

    fun backPreseedAndShowRanking(view: View) {
        backButtonVisibility.set(false)
        requestedSearchWord.set("")
        requestSubwayList(null)
    }

    fun showOrCloseFilter(view: View) {
        hideSoftKeyboard(activity)
        filterVisibility.set(!filterVisibility.get()!!)
    }

    fun requestRecentWordList() {
        val searchwordList = ArrayList<String>()
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val base = realm.where(SearchWordTable::class.java)
            val searchwordRealmList: ArrayList<SearchWordTable> = ArrayList(base.findAll())
            searchwordRealmList.forEach {
                it.search_word?.let { it1 -> searchwordList.add(it1) }
            }
        }
        rankingContract.showRecentSerachList(searchwordList)
    }

    val searchBoxClickListenr = object : View.OnClickListener {
        override fun onClick(v: View?) {
            (v as EditText).setText("", TextView.BufferType.EDITABLE)
            requestRecentWordList()
        }
    }

    val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {}
    }

    fun deleteAllSearchWord(view: View) {
        deleteAllRecentSearchWordFromDB()
        requestRecentWordList()
    }

    inner class StartSerachListener : TextView.OnEditorActionListener {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                saveRecentSerachWordToDB(v?.text.toString())
                if (v?.text.toString() == "") {
                    requestSubwayList(null)
                } else {
                    requestSubwayList(v?.text.toString())
                }
                return true
            }
            return false
        }
    }


}

