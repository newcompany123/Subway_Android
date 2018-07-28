package custom.subway.subway.Ranking

import android.content.Context
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import custom.subway.subway.Model.Subway


class RankingItemViewModel(
        val context: Context,
        val subway: Subway,
        val position: Int
) {

    val detailVisivility: ObservableField<Boolean> = ObservableField()
    var marginLeftOrRight: Boolean = true

    init {
        detailVisivility.set(false)
        if ((position + 1) % 2 == 0) marginLeftOrRight = false else true
        Log.d("bsbs", "" + subway)
    }

    fun itemOnClick(view: View) {
        detailVisivility.set(!detailVisivility.get()!!)
    }


    fun likeOnClick(view: View) {
//        APIClient(SubwayApplication.getSubwayApplicationContext()!!)
//                .getAPIService()
//                .likeThisSandwich(subway.id.toString())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeBy {
//                    when (subway.auth_user_like_state) {
//                        true -> subway.auth_user_like_state = false
//                        false -> subway.auth_user_like_state = true
//                    }
//                }
    }


}