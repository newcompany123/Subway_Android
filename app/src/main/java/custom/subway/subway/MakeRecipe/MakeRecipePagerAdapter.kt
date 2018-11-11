package custom.subway.subway.MakeRecipe

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import custom.subway.subway.MakeRecipe.BreadSelection.BreadSelectionFragment
import custom.subway.subway.MakeRecipe.CheeseSelection.CheeseSelectionFragment
import custom.subway.subway.MakeRecipe.NameSelection.NameSelectionFragmnet
import custom.subway.subway.MakeRecipe.SauceSelection.SauceSelectionFragmnet
import custom.subway.subway.MakeRecipe.SubwaySelection.SubwaySelectionFragment
import custom.subway.subway.MakeRecipe.ToastingSelection.ToastingSelectionFragment
import custom.subway.subway.MakeRecipe.ToppingSelection.ToppingSelectionFragment
import custom.subway.subway.MakeRecipe.VegetableSelection.VegetableSelectionFragment


class MakeRecipePagerAdapter(
    context: Context,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments: ArrayList<Fragment> = ArrayList()
    private val tabTitleList: ArrayList<String> = ArrayList()

    init {
        add(SubwaySelectionFragment(), "샌드위치")
        add(BreadSelectionFragment(), "빵")
        add(ToppingSelectionFragment(), "추가토핑")
        add(CheeseSelectionFragment(), "치즈")
        add(ToastingSelectionFragment(), "토스팅")
        add(VegetableSelectionFragment(), "야채")
        add(SauceSelectionFragmnet(), "소스")
        add(NameSelectionFragmnet(), "이름 정하기")
    }




    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitleList.get(position)
    }

    fun add(fragment: Fragment, tabTitle: String) {
        fragments.add(fragment)
        tabTitleList.add(tabTitle)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    fun refreshFragments() {
        fragments.clear()
        add(SubwaySelectionFragment(), "샌드위치")
        add(BreadSelectionFragment(), "빵")
        add(ToppingSelectionFragment(), "추가토핑")
        add(CheeseSelectionFragment(), "치즈")
        add(ToastingSelectionFragment(), "토스팅")
        add(VegetableSelectionFragment(), "야채")
        add(SauceSelectionFragmnet(), "소스")
        add(NameSelectionFragmnet(), "이름 정하기")
    }


}