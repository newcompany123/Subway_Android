package custom.subway.subway.MakeRecipe.SubwaySelection

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.MakeRecipeActivity
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.Model.FilterList
import custom.subway.subway.R
import custom.subway.subway.databinding.SubwayselcetionItemBinding

class SubwaySelectionAdapter(
        var subwayList: FilterList,
        val activity: MakeRecipeActivity,
        val subwaySelectionContract: SubwaySelectionContract
) : RecyclerView.Adapter<SubwaySelectionAdapter.SubwaySelectionViewHolder>() {


    lateinit var binding: SubwayselcetionItemBinding

    fun changeSubwayList(newSubwayList: FilterList) {
        subwayList = newSubwayList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubwaySelectionViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.subwayselcetion_item, parent, false
        )
        return SubwaySelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subwayList.results.size
    }

    override fun onBindViewHolder(holder: SubwaySelectionViewHolder, position: Int) {
        subwayList.results.get(position)?.let { subway ->
            with(holder) {
                itemView.tag = subway
                bind(subway)
            }
        }
    }

    inner class SubwaySelectionViewHolder(
            private val binding: SubwayselcetionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subway: FilterItem) {
            with(binding) {
                subwaySelectionItemViewModel = SubwaySelectionItemViewModel(
                        subway = subway,
                        activty = activity,
                        subwaySelectionContract = subwaySelectionContract
                )
                subwayOnprocess = SubwayOnProcess
                executePendingBindings()
            }
        }
    }
}
