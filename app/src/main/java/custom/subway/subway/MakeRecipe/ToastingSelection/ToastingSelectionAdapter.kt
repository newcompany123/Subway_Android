package custom.subway.subway.MakeRecipe.ToastingSelection

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.Model.IngredientResult
import custom.subway.subway.R
import custom.subway.subway.databinding.ToastingselectionItemBinding

class ToastingSelectionAdapter(
        val toastingList: IngredientResult,
        val activity: Activity,
        val toastingSelectionContract: ToastingSelectionContract
) : RecyclerView.Adapter<ToastingSelectionAdapter.ToastingSelectionViewHolder>() {


    lateinit var binding: ToastingselectionItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToastingSelectionViewHolder {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.toastingselection_item, parent, false
        )
        return ToastingSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return toastingList.results.size
    }

    override fun onBindViewHolder(holder: ToastingSelectionViewHolder, position: Int) {
        toastingList.results.get(position)?.let { toasting ->
            with(holder) {
                itemView.tag = toasting
                bind(toasting)
            }
        }
    }

    inner class ToastingSelectionViewHolder(
            private val binding: ToastingselectionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(toasting: IngredientItem) {
            with(binding) {
                toastingSelectionItemViewModel = ToastingSelectionItemViewModel(
                        toasting = toasting
                )
                subwayOnprocess = SubwayOnProcess
                executePendingBindings()
            }
        }
    }


}