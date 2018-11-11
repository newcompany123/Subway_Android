package custom.subway.subway.MakeRecipe.BreadSelection

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.R
import custom.subway.subway.databinding.BreadselectionItemBinding

class BreadSelectionAdapter(
        var breadList: IngredientResult,
        val activity: Activity,
        val breadSelectionContract: BreadSelctionContract
) : RecyclerView.Adapter<BreadSelectionAdapter.BreadSelectionViewHolder>() {

    lateinit var binding: BreadselectionItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreadSelectionViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.breadselection_item, parent, false
        )
        Log.d("ppp", "RE")
        return BreadSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return breadList.results.size
    }

    override fun onBindViewHolder(holder: BreadSelectionViewHolder, position: Int) {
        breadList.results.get(position)?.let { bread ->
            with(holder) {
                itemView.tag = bread
                bind(bread)
            }
        }
    }

    inner class BreadSelectionViewHolder(
            private val binding: BreadselectionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bread: IngredientItem) {
            with(binding) {
                breadSelectionItemViewModel = BreadSelectionItemViewModel(
                        bread = bread
                )
                Log.d("ppp", "bread 1: " +SubwayOnProcess.bread.get())
                subwayOnprocess = SubwayOnProcess
                executePendingBindings()
            }
        }
    }
}