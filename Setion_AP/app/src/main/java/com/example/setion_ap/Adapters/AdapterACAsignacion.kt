package com.example.setion_ap.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.setion_ap.APP.GestionColaboradores.Asignaciones.OpcionesAsignaciones
import com.example.setion_ap.Procedures.vProyectos
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class AdapterACAsignacion(context: Context, resource: Int, objects: ArrayList<vProyectos>):
    ArrayAdapter<vProyectos>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.__item_ac_asignacion, parent, false)
        }

        val nombreProyecto: TextView = convertView!!.findViewById(R.id.i__tvNombreProyecto__ACASIGNACION)
        val descripcion: TextView = convertView.findViewById(R.id.i__tvDescripcion__ACASIGNACION)
        val btn: Button = convertView.findViewById(R.id.i__btnGestionar__ACASIGNACION)

        // Establecer el texto en el TextView
        nombreProyecto.text = getItem(position)?.nombrePry
        descripcion.text = getItem(position)?.descripcion
        btn.setOnClickListener{fun_GestionarColaboradores(position)}

        return convertView
    }

    private fun fun_GestionarColaboradores(position: Int) {
        GP_VariableGlobales.AC_Proyecto = getItem(position)
        context.startActivity(Intent(context, OpcionesAsignaciones::class.java))
    }
}