package com.example.setion_ap.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.setion_ap.APP.GestionColaboradores.Asignaciones.OpcionesAsignaciones
import com.example.setion_ap.Procedures.vColaboradoresCompleto
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class AdapterACEliminarColaborador(context: Context, resource: Int, objects: ArrayList<vColaboradoresCompleto>):
    ArrayAdapter<vColaboradoresCompleto>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.__item_ac_anadir_colaborador, parent, false)
        }

        val nombreProyecto: TextView = convertView!!.findViewById(R.id.i__textView_ACANADIRCOLABOARADOR)
        val btn: Button = convertView.findViewById(R.id.i__btn_ACANADIRCOLABOARADOR)

        // Establecer el texto en el TextView
        nombreProyecto.text = getItem(position)?.nombreCompleto
        btn.setText("-")
        btn.setOnClickListener{fun_GestionarColaboradores(position)}

        return convertView
    }

    private fun fun_GestionarColaboradores(position: Int) {
        GP_VariableGlobales.AC_ArrayColaboradores.add(getItem(position)!!)
        Toast.makeText(context, getItem(position)?.nombreCompleto.toString() + " eliminado", Toast.LENGTH_SHORT).show()
        eliminarItem(getItem(position)!!)
    }

    fun eliminarItem(elemento: vColaboradoresCompleto) {
        // Eliminar el elemento de la lista de objetos
        remove(elemento)
        // Notificar al adaptador que los datos han cambiado
        notifyDataSetChanged()
    }
}