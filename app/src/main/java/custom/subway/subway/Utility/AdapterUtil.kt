package custom.subway.subway.Utility

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import custom.subway.subway.Model.MainIngredient
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
fun gravityBaseOnPosition(view: LinearLayout, position: Int) {
    // layout gravity
    if ((position + 1) % 2 == 0) {
        view.gravity = Gravity.LEFT
    } else {
        view.gravity = Gravity.RIGHT
    }
}

@BindingAdapter("mainComponent", "position", "context")
fun drawMainComponent(
        rootView: LinearLayout,
        mainComponent: ArrayList<MainIngredient>,
        position: Int,
        context: Context) {

    if (rootView.getChildAt(0) != null) return


    var rowCount = 0
    if (mainComponent.size < 3) rowCount = 1
    else rowCount = mainComponent.size % 3 + 1


    val inflater = LayoutInflater.from(context)
    val rowArrayList: ArrayList<LinearLayout> = ArrayList()
    val mainComponentList: ArrayList<List<MainIngredient>> = ArrayList()


    var temp = 0
    for (i in 0 until rowCount) {
        var max = (i + 1) * 3
        if (max > mainComponent.size) max = mainComponent.size
        mainComponentList.add(mainComponent.subList(temp, max))
        temp = max
        rowArrayList.add(LinearLayout(context).apply {
            this.orientation = LinearLayout.HORIZONTAL
        })
    }
    for (i in 0 until rowCount) {
        mainComponentList.get(i)?.let { list ->
            if (list.size == 3) {
                rowArrayList.get(i).gravity = Gravity.CENTER_HORIZONTAL
            } else {
                when ((position + 1) % 2) {
                    0 -> rowArrayList.get(i).gravity = Gravity.LEFT
                    1 -> rowArrayList.get(i).gravity = Gravity.RIGHT
                }
            }

            list.forEach {
                val componentView = inflater.inflate(R.layout.component, rowArrayList.get(i), false)
                componentView.findViewById<TextView>(R.id.component_name).text = it.name
                Glide.with(context).load(it.image3x)
                        .into(componentView.findViewById(R.id.component_img))
                rowArrayList.get(i).addView(componentView)
            }
        }
    }
    rowArrayList.forEach {
        rootView.addView(it)
    }


}

fun dpToPx(dp: Int): Int {
    val resource = SubwayApplication.getSubwayApplicationContext()?.resources
    val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resource!!.displayMetrics)
    return px.roundToInt()
}




























