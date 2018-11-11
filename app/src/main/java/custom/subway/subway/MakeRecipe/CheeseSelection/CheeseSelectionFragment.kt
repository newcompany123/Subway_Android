package custom.subway.subway.MakeRecipe.CheeseSelection

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
import custom.subway.subway.databinding.CheeseSelectionBinding

class CheeseSelectionFragment : Fragment(), CheeseSelectionContract {

    lateinit var binding: CheeseSelectionBinding
    lateinit var cheeseSelectionAdapter: CheeseSelectionAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: CheeseSelectionBinding = DataBindingUtil.inflate(inflater, R.layout.cheese_selection,
                container, false)
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.getBinding(view!!)!!
        binding.cheeseSelectionViewModel = CheeseSelectionViewModel(cheeseSelectionContract = this)
    }


    override fun showCheeseSelectionList(cheeseList: IngredientResult) {
        cheeseSelectionAdapter = CheeseSelectionAdapter(
                cheeseList = cheeseList,
                activity = activity as MakeRecipeActivity,
                cheeseSelectionContract = this@CheeseSelectionFragment
        )
        binding.cheeseSelectionRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cheeseSelectionAdapter
        }
    }
}