package com.example.setion_ap.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.R

class AdapterMI_Tareas(context: Context, resource: Int, objects: List<vProyectoTareas>):
    ArrayAdapter<vProyectoTareas>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.__item_mi_tareas, parent, false)
        }

        val textViewEcargado: TextView = convertView!!.findViewById(R.id.i__textViewEncargado__MI2)
        val textViewDescripcion: TextView = convertView.findViewById(R.id.i__textViewDescripcion__MI2)

        // Establecer el texto en el TextView
        textViewEcargado.text = getItem(position)?.nombEnc
        textViewDescripcion.text = getItem(position)?.descripcion

        return convertView
    }
}