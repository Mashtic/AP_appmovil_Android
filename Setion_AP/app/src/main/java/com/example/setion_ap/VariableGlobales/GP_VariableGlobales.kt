package com.example.setion_ap.VariableGlobales

import com.example.setion_ap.Procedures.vColaboradores
import com.example.setion_ap.Procedures.vProyectoTareas

class GP_VariableGlobales {
    companion object {
        var listaColaboradoresAnadidos: ArrayList<vColaboradores> = ArrayList()
        var listaTareasAnadidas: ArrayList<vProyectoTareas> = ArrayList()

        var userNombre:String = ""
        var userCedula:String = ""

        fun get_msjCorreoPorDefault(medio:String, fecha:String, hora:String, nivelImportancia:String):String{
            val msjCorreoPorDefault: String = "Buenas,\n" +
                    "Por este medio se les comparte la información con respecto a la próxima " +
                    "reunión vitual, por lo tanto la informmación es la siguiente:\n" +
                    "Medio: " + medio + "\n"+
                    "Fecha: " + fecha + "\n"+
                    "Hora: " + hora +  "\n" +
                    "Nivel de importancia: " + nivelImportancia + "\n" +
                    "Saludos."
            return msjCorreoPorDefault
        }
    }
}