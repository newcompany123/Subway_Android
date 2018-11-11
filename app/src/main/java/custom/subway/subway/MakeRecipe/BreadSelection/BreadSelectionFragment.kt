package custom.subway.subway.MakeRecipe.BreadSelection

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
import custom.subway.subway.databinding.BreadSelectionBinding

class BreadSelectionFragment : Fragment(), BreadSelctionContract {


    lateinit var binding: BreadSelectionBinding
    lateinit var breadSelectionAdapter: BreadSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: BreadSelectionBinding = DataBindingUtil.inflate(
            inflater, R.layout.bread_selection,
            container, false
        )
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.getBinding(view!!)!!
        binding.breadSelectionViewModel = BreadSelectionViewModel(breadSelctionContract = this)

    }

    override fun showBreadSelectionList(breadList: IngredientResult) {
        breadSelectionAdapter = BreadSelectionAdapter(
            breadList = breadList,
            activity = activity as MakeRecipeActivity,
            breadSelectionContract = this@BreadSelectionFragment
        )
        binding.breadSelectionRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = breadSelectionAdapter
        }
    }
}