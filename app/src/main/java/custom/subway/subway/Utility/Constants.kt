package custom.subway.subway.Utility

import android.databinding.ObservableField


object Constants {

    //Retrofit
    const val BASE_URL = "http://subway-eb.ap-northeast-2.elasticbeanstalk.com/"

    // User
    const val USER_TOKEN_PREF = "user_token"
    const val TOKEN_KEY = "token"
    const val EMPTY_TOKEN = "empty_token"

    val sortingOrderList: ArrayList<String> = ArrayList()
    val categoryList: ArrayList<String> = ArrayList()
    var observableSubwayFilter: ObservableSubwayFilter
    var selectedSortingOrder: ObservableField<String> = ObservableField()
    var seletedCategoryFilter: ObservableField<String> = ObservableField()


    init {
        sortingOrderList.apply {
            add("랭킹 높은 순")
            add("하트 많은 순")
            add("저장 많은 순")
            add("랭킹 높은 순")
        }
        selectedSortingOrder.set(sortingOrderList.get(0))
        categoryList.apply {
            add("모두")
            add("신제품")
            add("프로모션")
            add("클래식")
            add("프레쉬&라이트")
            add("프리미엄")
            add("아침메뉴")
        }
        seletedCategoryFilter.set(categoryList.get(0))
        observableSubwayFilter = ObservableSubwayFilter()
    }


    class ObservableSubwayFilter() {

        var subwayFilterMap: MutableMap<String, ObservableField<Boolean>> = mutableMapOf()

        fun addFilter(name: String) {
            subwayFilterMap.put(
                    name,
                    ObservableField<Boolean>().apply { set(false) }
            )
        }

        fun checkSelted(key: String) {
            subwayFilterMap.get(key)!!.set(!subwayFilterMap.get(key)!!.get()!!)
        }
    }


}
