package custom.subway.subway.Utility

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun hideSoftKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.getCurrentFocus()
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
}

fun getSortingOrderFilterInEnglish(koreanName: String): String {
    var result: String = ""
    when (koreanName) {
        "랭킹 높은순" -> result = ""
        "하트 많은 순" -> result = "like_count"
        "저장 많은 순" -> result = "bookmark_count"
    }
    return result
}

fun getCategoryFilterInEnglish(koreanName: String): String {
    var result: String = ""
    when (koreanName) {
        "모두" -> result = ""
        "신제품" -> result = "new"
        "프로모션" -> result = "event"
        "프레쉬&라이트" -> result = "fresh and light"
        "프리미엄" -> result = "premium"
        "아침메뉴" -> result = "breakfast"
    }
    return result
}