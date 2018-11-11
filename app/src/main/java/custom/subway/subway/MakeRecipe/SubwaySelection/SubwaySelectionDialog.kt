package custom.subway.subway.MakeRecipe.SubwaySelection

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.R
import custom.subway.subway.Utility.dpToPx
import kotlinx.android.synthetic.main.subwayselection_dialog.*


class SubwaySelectionDialog(
        context: Context,
        val subway: FilterItem) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE) //타이틀 바 삭제
        setContentView(R.layout.subwayselection_dialog)

        drawySubwayInfoDetail()
        setupButtons()
    }

    fun drawySubwayInfoDetail() {
        subwayTitle.text = subway.name
        serving_size.text = subway.serving_size.toString()
        calories.text = subway.calories.toString()
        sugars.text = subway.sugars.toString()
        protein.text = subway.protein.toString()
        saturated_fat.text = subway.saturated_fat.toString()
        sodium.text = subway.sodium.toString()

        val requestManager = Glide.with(context)

        val parentView = mainIngredientContainer
        for (i in 0 until subway.main_ingredient.size){

            val imageView = ImageView(context).apply {
                layoutParams = LinearLayout.LayoutParams(dpToPx(64), dpToPx(44)).apply {
                    setMargins(0,0, dpToPx(19),0)
                }
                requestManager.load(subway.main_ingredient.get(i).image3x).into(this)
            }
            parentView.addView(imageView)

        }
    }

    fun setupButtons() {
        close.setOnClickListener { dismiss() }
        total.setOnClickListener { dismiss() }
    }
}