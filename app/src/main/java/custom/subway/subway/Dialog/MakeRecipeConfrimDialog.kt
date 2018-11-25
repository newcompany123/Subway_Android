package custom.subway.subway.Dialog

import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import custom.subway.subway.R

class MakeRecipeConfrimDialog(

) : DialogFragment() {


    interface ConfirmOrNotNotifier{
        fun confirm()
        fun notConfirmed()
    }

    var confirmOrNotNotifier : ConfirmOrNotNotifier? = null
    fun addConfirOrNotListner(listener: ConfirmOrNotNotifier){
        confirmOrNotNotifier = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater?.inflate(R.layout.make_recipe_confirm_dialog, container, false)
        view!!.findViewById<TextView>(R.id.no).setOnClickListener {
            dismiss()
            confirmOrNotNotifier?.notConfirmed()
        }
        view!!.findViewById<TextView>(R.id.yes).setOnClickListener {
            Log.d("dididi", "YES")
            dismiss()
            confirmOrNotNotifier?.confirm()
        }
        return view!!
    }


    fun setupListener() {

    }
}