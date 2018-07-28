package custom.subway.subway.Utility

import android.content.Context
import android.databinding.BindingAdapter
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import custom.subway.subway.Model.*
import custom.subway.subway.R

@BindingAdapter("mainComponent", "position", "context")
fun drawMainComponent(
        rootView: LinearLayout,
        componentList: ArrayList<MainIngredient>,
        position: Int,
        context: Context) {

    if (rootView.getChildAt(0) != null) return
    if (componentList.isEmpty() || componentList == null)
        (rootView.parent as LinearLayout).visibility = View.GONE

    var rowCount = 0
    if (componentList.size < 3) rowCount = 1
    else rowCount = componentList.size % 3 + 1

    val inflater = LayoutInflater.from(context)
    val rowArrayList: ArrayList<LinearLayout> = ArrayList()
    val mainComponentList: ArrayList<List<MainIngredient>> = ArrayList()

    var temp = 0
    for (i in 0 until rowCount) {
        var max = (i + 1) * 3
        if (max > componentList.size) max = componentList.size
        mainComponentList.add(componentList.subList(temp, max))
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

@BindingAdapter("topping", "position", "context")
fun drawToppingComponent(
        rootView: LinearLayout,
        componentList: ArrayList<Topping>,
        position: Int,
        context: Context) {

    if (rootView.getChildAt(0) != null) return
    if (componentList.isEmpty() || componentList == null)
        (rootView.parent as LinearLayout).visibility = View.GONE

    var rowCount = 0
    if (componentList.size < 3) rowCount = 1
    else rowCount = componentList.size % 3 + 1

    val inflater = LayoutInflater.from(context)
    val rowArrayList: ArrayList<LinearLayout> = ArrayList()
    val mainComponentList: ArrayList<List<Topping>> = ArrayList()

    var temp = 0
    for (i in 0 until rowCount) {
        var max = (i + 1) * 3
        if (max > componentList.size) max = componentList.size
        mainComponentList.add(componentList.subList(temp, max))
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

@BindingAdapter("bread", "position", "context")
fun drawBreadComponent(
        rootView: LinearLayout,
        component: Bread,
        position: Int,
        context: Context) {

    if (component == null) (rootView.parent as LinearLayout).visibility = View.GONE
    if (rootView.getChildAt(0) != null) return

    when ((position + 1) % 2) {
        0 -> rootView.gravity = Gravity.LEFT
        1 -> rootView.gravity = Gravity.RIGHT
    }

    val inflater = LayoutInflater.from(context)
    val componentView = inflater.inflate(R.layout.component, rootView, false)
    componentView.findViewById<TextView>(R.id.component_name).text = component.name
    Glide.with(context).load(component.image3x).into(componentView.findViewById(R.id.component_img))
    rootView.addView(componentView)
}

@BindingAdapter("cheese", "position", "context")
fun drawCheeseComponent(
        rootView: LinearLayout,
        component: Cheese,
        position: Int,
        context: Context) {

    if (component == null) (rootView.parent as LinearLayout).visibility = View.GONE
    if (rootView.getChildAt(0) != null) return

    when ((position + 1) % 2) {
        0 -> rootView.gravity = Gravity.LEFT
        1 -> rootView.gravity = Gravity.RIGHT
    }

    val inflater = LayoutInflater.from(context)
    val componentView = inflater.inflate(R.layout.component, rootView, false)
    componentView.findViewById<TextView>(R.id.component_name).text = component.name
    Glide.with(context).load(component.image3x).into(componentView.findViewById(R.id.component_img))
    rootView.addView(componentView)
}


@BindingAdapter("vegetable", "position", "context")
fun drawVegetableComponent(
        rootView: LinearLayout,
        componentList: ArrayList<Vegetable>,
        position: Int,
        context: Context) {

    if (rootView.getChildAt(0) != null) return
    if (componentList.isEmpty() || componentList == null)
        (rootView.parent as LinearLayout).visibility = View.GONE

    var rowCount = 0
    if (componentList.size < 3) rowCount = 1
    else rowCount = componentList.size % 3 + 1

    val inflater = LayoutInflater.from(context)
    val rowArrayList: ArrayList<LinearLayout> = ArrayList()
    val mainComponentList: ArrayList<List<Vegetable>> = ArrayList()

    var temp = 0
    for (i in 0 until rowCount) {
        var max = (i + 1) * 3
        if (max > componentList.size) max = componentList.size
        mainComponentList.add(componentList.subList(temp, max))
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

@BindingAdapter("sauces", "position", "context")
fun drawVSauceComponent(
        rootView: LinearLayout,
        componentList: ArrayList<Sauces>,
        position: Int,
        context: Context) {

    if (rootView.getChildAt(0) != null) return
    if (componentList.isEmpty() || componentList == null)
        (rootView.parent as LinearLayout).visibility = View.GONE

    var rowCount = 0
    if (componentList.size < 3) rowCount = 1
    else rowCount = componentList.size % 3 + 1

    val inflater = LayoutInflater.from(context)
    val rowArrayList: ArrayList<LinearLayout> = ArrayList()
    val mainComponentList: ArrayList<List<Sauces>> = ArrayList()

    var temp = 0
    for (i in 0 until rowCount) {
        var max = (i + 1) * 3
        if (max > componentList.size) max = componentList.size
        mainComponentList.add(componentList.subList(temp, max))
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










