package custom.subway.subway.MakeRecipe.CheeseSelection

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.R
import custom.subway.subway.databinding.ChseeselectionItemBinding

class CheeseSelectionAdapter(
        val cheeseList: IngredientResult,
        val activity: Activity,
        val cheeseSelectionContract: CheeseSelectionContract
) : RecyclerView.Adapter<CheeseSelectionAdapter.CheeseSelectionViewHodler>() {

    lateinit var binding: ChseeselectionItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseSelectionViewHodler {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.chseeselection_item, parent, false
        )
        return CheeseSelectionViewHodler(binding)
    }

    override fun getItemCount(): Int {
        return cheeseList.results.size
    }

    override fun onBindViewHolder(holder: CheeseSelectionViewHodler, position: Int) {
        cheeseList.results.get(position)?.let { cheese ->
            with(holder) {
                itemView.tag = cheese
                bind(cheese)
            }
        }
    }

    inner class CheeseSelectionViewHodler(
            private val binding: ChseeselectionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cheese: IngredientItem) {
            with(binding) {
                cheeseSelectionItemViewModel = CheeseSelectionItemViewModel(
                        cheese = cheese
                )
                subwayOnprocess = SubwayOnProcess
                executePendingBindings()
            }
        }
    }

}