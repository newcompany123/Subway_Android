package custom.subway.subway.MakeRecipe.VegetableSelection

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.R
import custom.subway.subway.databinding.VegetableselectionItemBinding

class VegetableSelectionAdapter(
        val vegetableList: IngredientResult,
        val activity: Activity
) : RecyclerView.Adapter<VegetableSelectionAdapter.VegetableSelectionViewHolder>() {

    lateinit var binding: VegetableselectionItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegetableSelectionViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vegetableselection_item, parent, false
        )
        return VegetableSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return vegetableList.results.size
    }

    override fun onBindViewHolder(holder: VegetableSelectionViewHolder, position: Int) {
        vegetableList.results.get(position)?.let { vegetbale ->
            with(holder) {
                itemView.tag = vegetbale
                bind(vegetbale)
            }
        }
    }

    inner class VegetableSelectionViewHolder(
            private val binding: VegetableselectionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vegetable: IngredientItem) {
            with(binding) {
                vegetableSelectionItemViewModel = VegetableSelectionItemViewModel(
                        vegetable = vegetable
                )
                subwayOnprocess = SubwayOnProcess
                executePendingBindings()
            }
        }
    }
}