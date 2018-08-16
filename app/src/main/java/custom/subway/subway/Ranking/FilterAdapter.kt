package custom.subway.subway.Ranking

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.Model.FilterList
import custom.subway.subway.R
import custom.subway.subway.databinding.FilterItemBinding

class FilterAdapter(
        val filterList: FilterList,
        val context: Context

) : RecyclerView.Adapter<FilterAdapter.FilterListAdpaterViewHolder>() {


    lateinit var binding: FilterItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterListAdpaterViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.filter_item, parent, false
        )
        return FilterListAdpaterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filterList.results.size
    }

    override fun onBindViewHolder(holder: FilterListAdpaterViewHolder, position: Int) {
        filterList.results.get(position)?.let { filterItem ->
            with(holder) {
                itemView.tag = filterItem
                bind(filterItem, position)
            }
        }
    }

    class FilterListAdpaterViewHolder(
            private val binding: FilterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filterItem: FilterItem, position: Int) {
            with(binding) {
                filterItemViewModel = FilterItemViewModel(
                        filterItem =filterItem,
                        context = itemView.context
                )
                executePendingBindings()
            }
        }
    }
}