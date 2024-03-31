package com.example.setion_ap.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.Procedures.vProyectos
import com.example.setion_ap.R

class AdapterMI_ModificarTarea(context: Context, resource: Int, objects: List<vProyectos>):
    ArrayAdapter<vProyectos>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.__item_mi_modificar_tarea, parent, false)
        }

        val textView: TextView = convertView!!.findViewById(R.id.i__textView__MI)

        // Establecer el texto en el TextView
        textView.text = getItem(position)?.nombrePry

        return convertView
    }
}