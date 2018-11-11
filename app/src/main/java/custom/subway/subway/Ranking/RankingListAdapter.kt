package custom.subway.subway.Ranking

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.Model.Subway
import custom.subway.subway.Model.SubwayList
import custom.subway.subway.R
import custom.subway.subway.databinding.RankingItemBinding


class RankingListAdapter(
        val subwayList: SubwayList,
        val context: Context
) : RecyclerView.Adapter<RankingListAdapter.RankingListAdapterViewHolder>() {

    lateinit var binding: RankingItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingListAdapterViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.ranking_item, parent, false
        )
        return RankingListAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subwayList.results.size
    }

    override fun onBindViewHolder(holder: RankingListAdapterViewHolder, position: Int) {
        subwayList.results.get(position)?.let { subway ->
            with(holder) {
                itemView.tag = subway
                bind(subway, position)
            }
        }
    }

    class RankingListAdapterViewHolder(
            private val binding: RankingItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subway: Subway, position: Int) {
            binding.subwayComponentView!!.rankingItemViewModel = RankingItemViewModel(itemView.context, subway, position)

            with(binding) {
                rankingItemViewModel = RankingItemViewModel(
                        itemView.context,
                        subway,
                        position
                )
                executePendingBindings()
            }
        }
    }


}