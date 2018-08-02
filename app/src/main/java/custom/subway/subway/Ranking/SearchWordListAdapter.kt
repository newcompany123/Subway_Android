package custom.subway.subway.Ranking

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.R
import custom.subway.subway.databinding.SearchwordItemBinding

class SearchWordListAdapter(
        val rankingContract: RankingContract,
        val searchWordList: List<String>?,
        val context: Context
) : RecyclerView.Adapter<SearchWordListAdapter.SearchWordListAdapterViewHolder>() {

    lateinit var binding: SearchwordItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchWordListAdapterViewHolder {

        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.searchword_item, parent, false
        )
        return SearchWordListAdapterViewHolder(binding, rankingContract)
    }

    override fun getItemCount(): Int {
        return searchWordList?.size ?: 0
    }

    override fun onBindViewHolder(holder: SearchWordListAdapterViewHolder, position: Int) {
        searchWordList?.get(position)?.let { searchWord ->
            with(holder) {
                itemView.tag = searchWord
                bind(searchWord, position)
            }
        }
    }

    class SearchWordListAdapterViewHolder(
            private val binding: SearchwordItemBinding,
            private val rankingContract: RankingContract
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchWord: String, position: Int) {
            with(binding) {
                searchWordItemViewModel = SearchWordItemViewModel(
                        context = itemView.context,
                        searchWord = searchWord,
                        position = position,
                        rankingContract = rankingContract
                )
                executePendingBindings()
            }
        }
    }
}