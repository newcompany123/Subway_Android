package custom.subway.subway.MakeRecipe.SauceSelection

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.R
import custom.subway.subway.databinding.SauceselectionItemBinding

class SauceSelectionAdapter(
        val sauceList: IngredientResult,
        val activity: Activity
) : RecyclerView.Adapter<SauceSelectionAdapter.SauceSelectionViewHolder>() {


    lateinit var binding: SauceselectionItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SauceSelectionViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.sauceselection_item, parent, false
        )
        return SauceSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sauceList.results.size
    }

    override fun onBindViewHolder(holder: SauceSelectionViewHolder, position: Int) {
        sauceList.results.get(position)?.let { sauce ->
            with(holder) {
                itemView.tag = sauce
                bind(sauce)
            }
        }
    }

    inner class SauceSelectionViewHolder(
            private val binding: SauceselectionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sauce: IngredientItem) {
            with(binding) {
                sauceSelectionItemViewModel = SauceSelectionItemViewModel(
                        sauce = sauce
                )
                subwayOnprocess = SubwayOnProcess
                executePendingBindings()
            }
        }
    }
}