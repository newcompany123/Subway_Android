package custom.subway.subway.Ranking

import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import custom.subway.subway.API_Client.APIClient
import custom.subway.subway.LocalDB.SearchWordTable
import custom.subway.subway.Utility.SubwayApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class RankingViewModel(
        val rankingContract: RankingContract
) {

    var searchBoxVisibility: ObservableField<Boolean> = ObservableField()
    val startSerachListener: StartSerachListener

    init {
        requestSubwayList(null)
        searchBoxVisibility.set(false)
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
                APIClient(application = SubwayApplication.getSubwayApplicationContext()!!)
                        .getAPIService()
                        .getAllProductListBySearchWord(searchWrod)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {
                                    searchBoxVisibility.set(false)
                                    rankingContract.showRanking(it)
                                },
                                onError = {
                                }
                        )
            }
        }

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
                saveRecentSerachWord(v?.text.toString())
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

    // 중복 저장 방지 부터 해야됨
    fun saveRecentSerachWord(word: String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->

            with(realm.createObject(SearchWordTable::class.java)) {
                this.user_id = 1
                this.search_word = word
            }
        }
    }


}