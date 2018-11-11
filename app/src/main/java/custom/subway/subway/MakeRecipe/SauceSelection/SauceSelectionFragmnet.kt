package custom.subway.subway.MakeRecipe.SauceSelection

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.MakeRecipeActivity
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.R
import custom.subway.subway.databinding.SauceSelectionBinding


class SauceSelectionFragmnet : Fragment(), SauceSelectionContract {

    lateinit var binding: SauceSelectionBinding
    lateinit var sauceSelectionAdapter: SauceSelectionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: SauceSelectionBinding = DataBindingUtil.inflate(inflater, R.layout.sauce_selection,
                container, false)
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.getBinding(view!!)!!
        binding.sauceSelectionViewModel = SauceSelectionViewModel(
                sauceSelectionContract = this
        )
    }

    override fun showSauceList(sauceList: IngredientResult) {
        sauceSelectionAdapter = SauceSelectionAdapter(
                sauceList = sauceList,
                activity = activity as MakeRecipeActivity
        )
        binding.sauceSelectionRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3, Gravity.CENTER_HORIZONTAL, false)
            adapter = sauceSelectionAdapter
        }
    }
}
