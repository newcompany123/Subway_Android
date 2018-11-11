package custom.subway.subway.MakeRecipe.ToppingSelection

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
import custom.subway.subway.databinding.ToppingSelectionBinding

class ToppingSelectionFragment : Fragment(), ToppingSelectionContract {


    lateinit var binding: ToppingSelectionBinding
    lateinit var toppingSelectionAdapter: ToppingSelectionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: ToppingSelectionBinding = DataBindingUtil.inflate(inflater, R.layout.topping_selection,
                container, false)
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.getBinding(view!!)!!
        binding.toppingSelectionViewModel = ToppingSelectionViewModel(
                toppingSelectionContract = this
        )
    }

    override fun showToppingList(toppingList: IngredientResult) {
        toppingSelectionAdapter = ToppingSelectionAdapter(
                toppingList = toppingList,
                activity = activity as MakeRecipeActivity
        )
        binding.toppingSelectionRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3, Gravity.CENTER_HORIZONTAL, false)
            adapter = toppingSelectionAdapter
        }
    }


}