package custom.subway.subway.MakeRecipe.SubwaySelection

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.MakeRecipeActivity
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.Model.FilterList
import custom.subway.subway.R
import custom.subway.subway.Utility.Constants
import custom.subway.subway.databinding.SubwaySelectionBinding


class SubwaySelectionFragment : Fragment(), SubwaySelectionContract {

    lateinit var binding: SubwaySelectionBinding
    lateinit var subwaySelectionAdapter: SubwaySelectionAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: SubwaySelectionBinding = DataBindingUtil.inflate(inflater, R.layout.subway_selection, container, false)
        return view.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.getBinding(view!!)!!
        binding.subwaySelectionViewModel = SubwaySelectionViewModel(subwaySelectionContract = this)
        binding.filter = Constants
    }

    override fun showSubwaySelectionList(filterList: FilterList) {
        subwaySelectionAdapter = SubwaySelectionAdapter(
                subwayList = filterList,
                activity = activity as MakeRecipeActivity,
                subwaySelectionContract = this@SubwaySelectionFragment
        )
        binding.subwaySelectionRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = subwaySelectionAdapter
        }
    }

    override fun sortedSubwaySelectionList(filterList: FilterList) {
        binding.subwaySelectionRecyclerView.adapter?.let {
            subwaySelectionAdapter.changeSubwayList(filterList)
        }
    }

    override fun showSubwayInfo(subway: FilterItem) {
        SubwaySelectionDialog(
                context = this!!.context!!,
                subway = subway
        ).apply {
            setCancelable(true)
        }.show()
    }

    override fun showBreadSelectionList(filterList: FilterList) {
    }
}