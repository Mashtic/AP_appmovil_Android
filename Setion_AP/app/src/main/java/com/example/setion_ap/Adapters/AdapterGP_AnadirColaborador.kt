package com.example.setion_ap.Adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.setion_ap.Procedures.vColaboradores
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class AdapterGP_AnadirColaborador (context: Context, resource: Int, objects: List<vColaboradores>):
    ArrayAdapter<vColaboradores>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.__item_gp_anadir_colaborador, parent, false)
        }

        val textView: TextView = convertView!!.findViewById(R.id.i__textView__GPANADIRCOLABORADOR)
        val cardview: CardView = convertView.findViewById(R.id.i__cardView__GPANADIRCOLABORADOR)

        // Establecer el texto en el TextView
        textView.text = getItem(position)?.nombreCompleto

        return convertView
    }
}