package custom.subway.subway.MakeRecipe.ToppingSelection

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.R
import custom.subway.subway.databinding.ToppingselectionItemBinding

class ToppingSelectionAdapter(
        var toppingList: IngredientResult,
        val activity: Activity
) : RecyclerView.Adapter<ToppingSelectionAdapter.ToppingSelectionViewHolder>() {


    lateinit var binding: ToppingselectionItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToppingSelectionViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.toppingselection_item, parent, false
        )
        return ToppingSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return toppingList.results.size
    }

    override fun onBindViewHolder(holder: ToppingSelectionViewHolder, position: Int) {
        toppingList.results.get(position)?.let { topping ->
            with(holder) {
                itemView.tag = topping
                bind(topping)
            }
        }
    }

    inner class ToppingSelectionViewHolder(
            private val binding: ToppingselectionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(topping: IngredientItem) {
            with(binding) {
                toppingSelectionItemViewModel = ToppingSelectionItemViewModel(
                        topping= topping
                )
                subwayOnprocess = SubwayOnProcess
                executePendingBindings()
            }
        }
    }
}