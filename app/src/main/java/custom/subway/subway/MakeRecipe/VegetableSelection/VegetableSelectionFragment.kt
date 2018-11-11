package custom.subway.subway.MakeRecipe.VegetableSelection

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.MakeRecipeActivity
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.R
import custom.subway.subway.databinding.VegetableSelectionBinding

class VegetableSelectionFragment : Fragment(), VegetableSelectionContract {


    lateinit var binding: VegetableSelectionBinding
    lateinit var vegetableSelectionAdapter: VegetableSelectionAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: VegetableSelectionBinding = DataBindingUtil.inflate(inflater, R.layout.vegetable_selection,
                container, false)
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.getBinding(view!!)!!
        binding.vegetableSelectionViewModel = VegetableSelectionViewModel(
                vegetableSelectionContract = this
        )
    }

    override fun showVegetableSelectionList(vegetableList: IngredientResult) {
        vegetableSelectionAdapter = VegetableSelectionAdapter(
                vegetableList = vegetableList,
                activity = activity as MakeRecipeActivity
        )
        binding.vegetableSelectionRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = vegetableSelectionAdapter
        }
    }
}