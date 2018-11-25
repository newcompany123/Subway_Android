package custom.subway.subway.Utility

import android.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.Typeface
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import custom.subway.subway.MakeRecipe.MakeRecipeActivity
import custom.subway.subway.MakeRecipe.MakeRecipePagerAdapter
import custom.subway.subway.MakeRecipe.SubwayOnProcess
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.Model.IngredientItem
import custom.subway.subway.Model.Sandwich
import custom.subway.subway.R
import kotlin.math.roundToInt


@BindingAdapter("android:layout_margin")
fun setMargin(view: View, margin: Float) {
    val lp = view.layoutParams as ViewGroup.MarginLayoutParams
    lp.setMargins(margin.toInt(), margin.toInt(), margin.toInt(), margin.toInt())
    view.layoutParams = lp
}

@BindingAdapter("bind:font")
fun setFont(textView: TextView, fontName: String) {
    textView.setTypeface(
        Typeface.createFromAsset(
            textView.getContext().getAssets(),
            "fonts/" + fontName
        )
    );
}

@BindingAdapter("sandwich", "index")
fun imageFromUrl(view: ImageView, sandwich: Sandwich, index: Int) {
    sandwich?.let {
        if ((index + 1) % 2 == 0) {
            Glide.with(view.context)
                .load(sandwich.image3x_left)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        } else {
            Glide.with(view.context)
                .load(sandwich.image3x_right)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }
}

@BindingAdapter("filterItem")
fun imageFromUrl(view: ImageView, filterItem: FilterItem) {
    Log.d("filter", "")
    Glide.with(view.context)
        .load(filterItem.image)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}

@BindingAdapter("ingredientImage")
fun imageFromUrl(view: ImageView, ingredientItem: IngredientItem) {
    Log.d("filter", "")
    Glide.with(view.context)
        .load(ingredientItem.image)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}

@BindingAdapter("marginBaseOnPosition")
fun marginBaseOnPosition(view: View, position: Int) {
    val lp = view.layoutParams as ViewGroup.MarginLayoutParams
    if ((position + 1) % 2 == 0) {
        lp.setMargins(0, 0, dpToPx(19), 0)
    } else {
        lp.setMargins(dpToPx(19), 0, 0, 0)
    }
    view.layoutParams = lp
}

@BindingAdapter("gravityBaseOnPosition")
fun linearLayoutGravityBaseOnPosition(view: LinearLayout, position: Int) {
    if ((position + 1) % 2 == 0) view.gravity = Gravity.LEFT
    else view.gravity = Gravity.RIGHT
}

@BindingAdapter("gravityBaseOnPosition")
fun textViewGravityBaseOnposition(view: TextView, position: Int) {
    if ((position + 1) % 2 == 0) view.gravity = Gravity.LEFT
    else view.gravity = Gravity.RIGHT
}

fun dpToPx(dp: Int): Int {
    val resource = SubwayApplication.getSubwayApplicationContext()?.resources
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resource!!.displayMetrics
    )
    return px.roundToInt()
}

@BindingAdapter("onFocusChange")
fun onFocusChange(text: EditText, listener: View.OnFocusChangeListener) {
    text.onFocusChangeListener = listener
}

@BindingAdapter("onEditTextActionListener")
fun onEditTextActionListener(text: EditText, listener: TextView.OnEditorActionListener) {
    text.setOnEditorActionListener(listener)
}

@BindingAdapter("bind:handler")
fun bindPagerAdapter(view: ViewPager, activity: MakeRecipeActivity) {
    MakeRecipePagerAdapter(view.context, activity.supportFragmentManager)?.let {
        view.adapter = it
    }
}

@BindingAdapter("bind:pager")
fun bindPagerTab(tabView: TabLayout, pager: ViewPager) {
    tabView.setupWithViewPager(pager, true)

//    val tabStrip = tabView.getChildAt(0) as LinearLayout
//
//    for (i in 0 until tabStrip.childCount) {
//        tabStrip.getChildAt(i).setOnTouchListener { v, event ->
//            Log.d("ttt", "HERE")
//            true
//        }
//    }


//    val tabStrip = tabView.getChildAt(0) as LinearLayoutCompat
//    Log.d("ttt", "1 : "+     tabView.getChildAt(0).layoutParams
//    )
//    Log.d("ttt", "1 : "+ tabView.childCount)
//
//    tabStrip.isEnabled = false
//    for (i in 0 until tabStrip.childCount) {
//        tabStrip.getChildAt(i).isClickable = false
//    }

}

@BindingAdapter("bind:followCurrentPage")
fun followCurrentPage(tabLayout: TabLayout, abc: Int) {
    val currentProcess = SubwayOnProcess.getCurrentProcess()
    tabLayout.getTabAt(currentProcess)?.select()
}


@BindingAdapter(
    "bind:isVegetableAmoutIsSelceted_vegetable",
    "bind:isVegetableAmoutIsSelceted_amount"
)
fun isVegetableAmoutIsSelceted(view: View, vegetable: IngredientItem, amount: Int) {
    when (SubwayOnProcess.vegetables.get(vegetable.name)) {
        null -> view.setBackgroundResource(0)
        else -> {
            if (SubwayOnProcess.vegetables.getValue(vegetable.name) == amount) {
                view.setBackgroundResource(R.drawable.filter_item_background_2)
                (view as TextView).setTextColor(Color.parseColor("#ffffff"))
            } else {
                view.setBackgroundResource(0)
                (view as TextView).setTextColor(Color.parseColor("#717171"))
            }
        }
    }
}
