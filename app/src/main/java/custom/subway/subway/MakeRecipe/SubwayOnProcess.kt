package custom.subway.subway.MakeRecipe

import android.databinding.ObservableArrayList
import android.databinding.ObservableArrayMap
import android.databinding.ObservableField

object SubwayOnProcess {

    var sandwich: ObservableField<String> = ObservableField()
    var bread: ObservableField<String> = ObservableField()
    var toppings: ObservableArrayList<String> = ObservableArrayList()
    var cheese: ObservableField<String> = ObservableField()
    var toasting: ObservableField<String> = ObservableField()
    var vegetables: ObservableArrayMap<String, Int> = ObservableArrayMap()
    var sauces: ObservableArrayList<String> = ObservableArrayList()
    var name: ObservableField<String> = ObservableField()


    var SANDWICH_PROCESS_COMPLETED: ObservableField<Boolean> = ObservableField()
    var BREAD_PROCESS_COMPLETED: ObservableField<Boolean> = ObservableField()
    var TOPPINGS_PROCESS_COMPLETED: ObservableField<Boolean> = ObservableField()
    var CHEESE_PROCESS_COMPLETED: ObservableField<Boolean> = ObservableField()
    var TOASTING_PROCESS_COMPLETED: ObservableField<Boolean> = ObservableField()
    var VEGETABLES_PROCESS_COMPLETED: ObservableField<Boolean> = ObservableField()
    var SAUCES_PROCESS_COMPLETED: ObservableField<Boolean> = ObservableField()
    var NAME_PROCESS_COMPLETED: ObservableField<Boolean> = ObservableField()

    var CURRENT_PAGE: ObservableField<Int> = ObservableField()


    init {
        SANDWICH_PROCESS_COMPLETED.set(false)
        BREAD_PROCESS_COMPLETED.set(false)
        TOPPINGS_PROCESS_COMPLETED.set(false)
        CHEESE_PROCESS_COMPLETED.set(false)
        TOASTING_PROCESS_COMPLETED.set(false)
        VEGETABLES_PROCESS_COMPLETED.set(false)
        SAUCES_PROCESS_COMPLETED.set(false)
        NAME_PROCESS_COMPLETED.set(false)

        CURRENT_PAGE.set(0)
    }


    fun clearProcess() {
        NAME_PROCESS_COMPLETED.set(false)
        SANDWICH_PROCESS_COMPLETED.set(false)
        BREAD_PROCESS_COMPLETED.set(false)
        TOPPINGS_PROCESS_COMPLETED.set(false)
        CHEESE_PROCESS_COMPLETED.set(false)
        TOASTING_PROCESS_COMPLETED.set(false)
        VEGETABLES_PROCESS_COMPLETED.set(false)
        SAUCES_PROCESS_COMPLETED.set(false)

        CURRENT_PAGE.set(0)

        sandwich.set("refresh")
        bread.set("refresh")
        toppings.clear()
        cheese.set("refresh")
        toasting.set("refresh")
        vegetables.clear()
        sauces.clear()
        name.set("refresh")
    }




    fun addOrUpdateVegetable(name: String, amount: Int) {
        when (vegetables.get(name)) {
            null -> vegetables.put(name, amount)
            else -> {
                vegetables.remove(name)
                vegetables.put(name, amount)
            }
        }
    }

    fun getCurrentProcess(): Int {
        var result: Int = 0
        val processList = ArrayList<ObservableField<Boolean>>()
        processList.apply {
            add(SANDWICH_PROCESS_COMPLETED)
            add(BREAD_PROCESS_COMPLETED)
            add(TOPPINGS_PROCESS_COMPLETED)
            add(CHEESE_PROCESS_COMPLETED)
            add(TOASTING_PROCESS_COMPLETED)
            add(VEGETABLES_PROCESS_COMPLETED)
            add(SAUCES_PROCESS_COMPLETED)
            add(NAME_PROCESS_COMPLETED)
        }
        for (i in 0 until processList.size - 1) {
            if (processList.get(i).get() == true) {
                result = result + 1
            }
        }
        return result
    }


    fun setCurrentProccess(completedProcess: Int) {
        when (completedProcess) {
            1 -> if (isCompletedProcessIsCorret(completedProcess)) {
                SANDWICH_PROCESS_COMPLETED.set(true)
                CURRENT_PAGE.set(1)
            }
            2 -> if (isCompletedProcessIsCorret(completedProcess)) {
                BREAD_PROCESS_COMPLETED.set(true)
                CURRENT_PAGE.set(2)
            }
            3 -> if (isCompletedProcessIsCorret(completedProcess)) {
                TOPPINGS_PROCESS_COMPLETED.set(true)
                CURRENT_PAGE.set(3)
            }
            4 -> if (isCompletedProcessIsCorret(completedProcess)) {
                CHEESE_PROCESS_COMPLETED.set(true)
                CURRENT_PAGE.set(4)
            }
            5 -> if (isCompletedProcessIsCorret(completedProcess)) {
                TOASTING_PROCESS_COMPLETED.set(true)
                CURRENT_PAGE.set(5)
            }
            6 -> if (isCompletedProcessIsCorret(completedProcess)) {
                VEGETABLES_PROCESS_COMPLETED.set(true)
                CURRENT_PAGE.set(6)
            }
            7 -> if (isCompletedProcessIsCorret(completedProcess)) {
                SAUCES_PROCESS_COMPLETED.set(true)
                CURRENT_PAGE.set(7)
            }
            8 -> if (isCompletedProcessIsCorret(completedProcess)) {
                NAME_PROCESS_COMPLETED.set(true)
                CURRENT_PAGE.set(8)
            }
        }
    }

    fun isCompletedProcessIsCorret(completedProcess: Int): Boolean {
        when (completedProcess) {
            1 -> return true
            2 -> if (SANDWICH_PROCESS_COMPLETED.get()!!) {
                return isCompletedProcessIsCorret(completedProcess - 1)
            }
            3 -> if (BREAD_PROCESS_COMPLETED.get()!!) {
                return isCompletedProcessIsCorret(completedProcess - 1)
            }
            4 -> if (TOPPINGS_PROCESS_COMPLETED.get()!!) {
                return isCompletedProcessIsCorret(completedProcess - 1)
            }
            5 -> if (CHEESE_PROCESS_COMPLETED.get()!!) {
                return isCompletedProcessIsCorret(completedProcess - 1)
            }
            6 -> if (TOASTING_PROCESS_COMPLETED.get()!!) {
                return isCompletedProcessIsCorret(completedProcess - 1)
            }
            7 -> if (VEGETABLES_PROCESS_COMPLETED.get()!!) {
                return isCompletedProcessIsCorret(completedProcess - 1)
            }
            8 -> if (SAUCES_PROCESS_COMPLETED.get()!!) {
                return isCompletedProcessIsCorret(completedProcess - 1)
            }
            else -> return false
        }
        return false
    }

    fun checkTargetProcessIsAvailable(targetProcess: Int): Boolean {
        when (targetProcess) {
            0 -> return true
            1 -> return SANDWICH_PROCESS_COMPLETED.get()!!
            2 -> return BREAD_PROCESS_COMPLETED.get()!!
            3 -> return TOPPINGS_PROCESS_COMPLETED.get()!!
            4 -> return CHEESE_PROCESS_COMPLETED.get()!!
            5 -> return TOASTING_PROCESS_COMPLETED.get()!!
            6 -> return VEGETABLES_PROCESS_COMPLETED.get()!!
            7 -> return SAUCES_PROCESS_COMPLETED.get()!!
        }
        return false
    }

    fun addOrRemoveTopping(topping: String): Boolean {
        when (toppings.find { it == topping }) {
            null -> {
                toppings.add(topping)
                return true
            }
            else -> {
                toppings.remove(topping)
                return false
            }
        }
        return false
    }

    fun addOrRemoveSauce(sauce: String): Boolean {
        when (sauces.find { it == sauce }) {
            null -> {
                sauces.add(sauce)
                return true
            }
            else -> {
                sauces.remove(sauce)
                return false
            }
        }
        return false
    }




}
