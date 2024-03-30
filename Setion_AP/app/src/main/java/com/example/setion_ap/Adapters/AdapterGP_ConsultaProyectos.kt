package com.example.setion_ap.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.setion_ap.R

class AdapterGP_ConsultaProyectos(context: Context, resource: Int, objects: List<String>):
    ArrayAdapter<String>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.__item_cp_consulta_proyectos, parent, false)
        }

        val textView: TextView = convertView!!.findViewById(R.id.i__textView)

        // Obtener el elemento en la posición actual
        val item = getItem(position)

        // Establecer el texto en el TextView
        textView.text = item

        // Establecer la imagen en el ImageView
        // Por ejemplo:
        // imageView.setImageResource(R.drawable.mi_imagen)

        return convertView
    }
}