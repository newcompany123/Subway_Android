package custom.subway.subway.Ranking

import android.content.Context
import android.databinding.ObservableField
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
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
    }

    fun itemOnClick(view: View) {
        val target = (view.parent as LinearLayout).getChildAt(2)
        when (detailVisivility.get()) {
            true -> collapse(target)
            false -> expand(target)
        }
    }


    fun expand(v: View) {
        v.measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 1
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 0f) detailVisivility.set(true)

                v.layoutParams.height = if (interpolatedTime == 1f)
                    WindowManager.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

    fun collapse(v: View) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    detailVisivility.set(false)

                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
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

