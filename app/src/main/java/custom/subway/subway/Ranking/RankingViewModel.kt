package custom.subway.subway.Ranking

import android.app.Activity
import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.LocalDB.SearchWordTable
import custom.subway.subway.LocalDB.deleteAllRecentSearchWordFromDB
import custom.subway.subway.LocalDB.saveRecentSerachWordToDB
import custom.subway.subway.Utility.SubwayApplication
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
    val startSerachListener: StartSerachListener

    init {
        requestSubwayList(null)
        searchBoxVisibility.set(false)
        backButtonVisibility.set(false)
        startSerachListener = StartSerachListener()
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
                                    hideSoftKeyboard()
                                },
                                onError = {
                                }
                        )
            }
        }
    }


    fun backPreseedAndShowRanking(view: View) {
        backButtonVisibility.set(false)
        requestedSearchWord.set("")
        requestSubwayList(null)
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

    fun deleteAllSearchWord(view: View) {
        deleteAllRecentSearchWordFromDB()
        requestRecentWordList()
    }

    fun hideSoftKeyboard() {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.getCurrentFocus()
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
    }


}

