package custom.subway.subway.Ranking

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.R
import custom.subway.subway.databinding.SerachwordItemBinding

class SearchWordListAdapter(
        val searchWordList: ArrayList<String>,
        val context: Context
) : RecyclerView.Adapter<SearchWordListAdapter.SearchWordListAdapterViewHolder>() {

    lateinit var binding: SerachwordItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchWordListAdapterViewHolder {

        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.serachword_item, parent, false
        )
        return SearchWordListAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchWordList.size
    }

    override fun onBindViewHolder(holder: SearchWordListAdapterViewHolder, position: Int) {
        searchWordList.get(position).let { searchWord ->
            with(holder) {
                itemView.tag = searchWord
                bind(searchWord, position)
            }
        }
    }

    class SearchWordListAdapterViewHolder(
            private val binding: SerachwordItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchWord: String, position: Int) {
            with(binding) {
                searchWordViewModel = SerachWordViewModel(
                        itemView.context,
                        searchWord,
                        position
                )
                executePendingBindings()
            }
        }
    }
}