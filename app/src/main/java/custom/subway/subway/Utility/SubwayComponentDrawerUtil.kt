package custom.subway.subway.Utility

import android.content.Context
import android.databinding.BindingAdapter
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


    if (mainComponentList.last().size != 3) {
        val temp = ArrayList<MainIngredient>()
        when (mainComponentList.last().size) {
            1 -> {
                when ((position + 1) % 2) {
                    0 -> {
                        temp.add(mainComponentList.last().get(0))
                        temp.add(MainIngredient(null, null, null, null, null))
                        temp.add(MainIngredient(null, null, null, null, null))
                    }
                    1 -> {
                        temp.add(MainIngredient(null, null, null, null, null))
                        temp.add(MainIngredient(null, null, null, null, null))
                        temp.add(mainComponentList.last().get(0))
                    }
                }

            }
            2 -> {
                when ((position + 1) % 2) {
                    0 -> {
                        temp.add(mainComponentList.last().get(0))
                        temp.add(mainComponentList.last().get(1))
                        temp.add(MainIngredient(null, null, null, null, null))
                    }
                    1 -> {
                        temp.add(MainIngredient(null, null, null, null, null))
                        temp.add(mainComponentList.last().get(0))
                        temp.add(mainComponentList.last().get(1))
                    }
                }

            }
        }
        mainComponentList.removeAt(mainComponentList.size - 1)
        mainComponentList.add(temp)
    }


    for (i in 0 until rowCount) {
        mainComponentList.get(i)?.let { list ->
            rowArrayList.get(i).gravity = Gravity.CENTER_HORIZONTAL
            list.forEach {
                var componentView = inflater.inflate(R.layout.component, rowArrayList.get(i), false)
                it.name?.let { componentView.findViewById<TextView>(R.id.component_name).text = it }
                it.image3x?.let { Glide.with(context).load(it).into(componentView.findViewById(R.id.component_img)) }
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

    if (mainComponentList.last().size != 3) {
        val temp = ArrayList<Topping>()
        when (mainComponentList.last().size) {
            1 -> {
                when ((position + 1) % 2) {
                    0 -> {
                        temp.add(mainComponentList.last().get(0))
                        temp.add(Topping(null, null, null, null))
                        temp.add(Topping(null, null, null, null))
                    }
                    1 -> {
                        temp.add(Topping(null, null, null, null))
                        temp.add(Topping(null, null, null, null))
                        temp.add(mainComponentList.last().get(0))
                    }
                }
            }
            2 -> {
                when ((position + 1) % 2) {
                    0 -> {
                        temp.add(mainComponentList.last().get(0))
                        temp.add(mainComponentList.last().get(1))
                        temp.add(Topping(null, null, null, null))
                    }
                    1 -> {
                        temp.add(Topping(null, null, null, null))
                        temp.add(mainComponentList.last().get(0))
                        temp.add(mainComponentList.last().get(1))
                    }
                }
            }
        }
        mainComponentList.removeAt(mainComponentList.size - 1)
        mainComponentList.add(temp)
    }

    for (i in 0 until rowCount) {
        mainComponentList.get(i)?.let { list ->
            rowArrayList.get(i).gravity = Gravity.CENTER_HORIZONTAL
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

    val temp = ArrayList<Bread>()
    when ((position + 1) % 2) {
        0 -> {
            temp.add(component)
            temp.add(Bread(null, null, null, null))
            temp.add(Bread(null, null, null, null))
        }
        1 -> {
            temp.add(Bread(null, null, null, null))
            temp.add(Bread(null, null, null, null))
            temp.add(component)
        }
    }

    val inflater = LayoutInflater.from(context)
    val parent = LinearLayout(context)
    parent.gravity = Gravity.CENTER_HORIZONTAL
    for (i in 0 until temp.size) {
        val componentView = inflater.inflate(R.layout.component, rootView, false)
        componentView.findViewById<TextView>(R.id.component_name).text = temp.get(i).name
        Glide.with(context).load(temp.get(i).image3x).into(componentView.findViewById(R.id.component_img))
        parent.addView(componentView)
    }
    rootView.addView(parent)
}

@BindingAdapter("cheese", "position", "context")
fun drawCheeseComponent(
        rootView: LinearLayout,
        component: Cheese,
        position: Int,
        context: Context) {

    if (component == null) (rootView.parent as LinearLayout).visibility = View.GONE
    if (rootView.getChildAt(0) != null) return

    val temp = ArrayList<Cheese>()
    when ((position + 1) % 2) {
        0 -> {
            temp.add(component)
            temp.add(Cheese(null, null, null, null))
            temp.add(Cheese(null, null, null, null))
        }
        1 -> {
            temp.add(Cheese(null, null, null, null))
            temp.add(Cheese(null, null, null, null))
            temp.add(component)
        }
    }

    val inflater = LayoutInflater.from(context)
    val parent = LinearLayout(context)
    parent.gravity = Gravity.CENTER_HORIZONTAL
    for (i in 0 until temp.size) {
        val componentView = inflater.inflate(R.layout.component, rootView, false)
        componentView.findViewById<TextView>(R.id.component_name).text = temp.get(i).name
        Glide.with(context).load(temp.get(i).image3x).into(componentView.findViewById(R.id.component_img))
        parent.addView(componentView)
    }
    rootView.addView(parent)
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

    if (mainComponentList.last().size != 3) {
        val temp = ArrayList<Vegetable>()
        when (mainComponentList.last().size) {
            1 -> {
                when ((position + 1) % 2) {
                    0 -> {
                        temp.add(mainComponentList.last().get(0))
                        temp.add(Vegetable(null, null, null, null, null))
                        temp.add(Vegetable(null, null, null, null, null))
                    }
                    1 -> {
                        temp.add(Vegetable(null, null, null, null, null))
                        temp.add(Vegetable(null, null, null, null, null))
                        temp.add(mainComponentList.last().get(0))
                    }
                }

            }
            2 -> {
                when ((position + 1) % 2) {
                    0 -> {
                        temp.add(mainComponentList.last().get(0))
                        temp.add(mainComponentList.last().get(1))
                        temp.add(Vegetable(null, null, null, null, null))
                    }
                    1 -> {
                        temp.add(Vegetable(null, null, null, null, null))
                        temp.add(mainComponentList.last().get(0))
                        temp.add(mainComponentList.last().get(1))
                    }
                }
            }
        }
        mainComponentList.removeAt(mainComponentList.size - 1)
        mainComponentList.add(temp)
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

    if (mainComponentList.last().size != 3) {
        val temp = ArrayList<Sauces>()
        when (mainComponentList.last().size) {
            1 -> {
                when ((position + 1) % 2) {
                    0 -> {
                        temp.add(mainComponentList.last().get(0))
                        temp.add(Sauces(null, null, null, null))
                        temp.add(Sauces(null, null, null, null))
                    }
                    1 -> {
                        temp.add(Sauces(null, null, null, null))
                        temp.add(Sauces(null, null, null, null))
                        temp.add(mainComponentList.last().get(0))
                    }
                }
            }
            2 -> {
                when ((position + 1) % 2) {
                    0 -> {
                        temp.add(mainComponentList.last().get(0))
                        temp.add(mainComponentList.last().get(1))
                        temp.add(Sauces(null, null, null, null))
                    }
                    1 -> {
                        temp.add(Sauces(null, null, null, null))
                        temp.add(mainComponentList.last().get(0))
                        temp.add(mainComponentList.last().get(1))
                    }
                }
            }
        }
        mainComponentList.removeAt(mainComponentList.size - 1)
        mainComponentList.add(temp)
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










