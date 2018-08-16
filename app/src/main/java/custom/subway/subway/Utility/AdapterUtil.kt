package custom.subway.subway.Utility

import android.databinding.BindingAdapter
import android.graphics.Typeface
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
import custom.subway.subway.Model.FilterItem
import custom.subway.subway.Model.Sandwich
import kotlin.math.roundToInt


@BindingAdapter("android:layout_margin")
fun setMargin(view: View, margin: Float) {
    val lp = view.layoutParams as ViewGroup.MarginLayoutParams
    lp.setMargins(margin.toInt(), margin.toInt(), margin.toInt(), margin.toInt())
    view.layoutParams = lp
}

@BindingAdapter("bind:font")
fun setFont(textView: TextView, fontName: String) {
    textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
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
    val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resource!!.displayMetrics)
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



