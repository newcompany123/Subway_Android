package custom.subway.subway.MakeRecipe.ToastingSelection

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
import custom.subway.subway.databinding.ToastingSelectionBinding

class ToastingSelectionFragment : Fragment(), ToastingSelectionContract {


    lateinit var binding: ToastingSelectionBinding
    lateinit var toastingSelectionAdapter: ToastingSelectionAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: ToastingSelectionBinding = DataBindingUtil.inflate(inflater, R.layout.toasting_selection,
                container, false)
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.getBinding(view!!)!!
        binding.toastingSelectionViewModel = ToastingSelectionViewModel(toastingSelectionContract = this)
    }

    override fun showToastingList(toastingList: IngredientResult) {
        toastingSelectionAdapter = ToastingSelectionAdapter(
                toastingList = toastingList,
                activity = activity as MakeRecipeActivity,
                toastingSelectionContract = this
        )
        binding.toastingSelectionRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = toastingSelectionAdapter
        }
    }
}