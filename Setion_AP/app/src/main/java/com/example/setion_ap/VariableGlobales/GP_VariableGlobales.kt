package com.example.setion_ap.VariableGlobales

import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vColaboradores
import com.example.setion_ap.Procedures.vColaboradoresCompleto
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.Procedures.vProyectos

class GP_VariableGlobales {
    companion object {

        var listaColaboradoresAnadidos: ArrayList<vColaboradores> = ArrayList()
        var listaTareasAnadidas: ArrayList<vProyectoTareas> = ArrayList()

        var userNombre:String = ""
        var userCedula:String = ""
        var idProyecto:Int? = null


        //VARARIABLES PARA GC
        var GC_Colaborador: vColaboradores? = null

        //VARIABLES EN AC
        var AC_Proyecto: vProyectos? = null
        var AC_ArrayColaboradores = ArrayList<vColaboradoresCompleto>()

        fun get_proyectoAsignado(): Int? {
            println("Ya pasamos por aca")
            if(idProyecto==null){
                val colaboradores = GP_Procedures.get_ColaboradoresCompleto()
                for (e in colaboradores) {
                    println(e.cedula + "   -   " + userCedula)
                    if (e.cedula.equals(userCedula)) {
                        idProyecto = e.proyecto
                        break
                    }
                }
            }
            return idProyecto
        }

        fun get_colaboradores_libres_completos():ArrayList<vColaboradoresCompleto>{
            var result = ArrayList<vColaboradoresCompleto>()
            val colaboradores = GP_Procedures.get_ColaboradoresCompleto()
            for (e in colaboradores){
                if (e.proyecto==0){
                    result.add(e)
                }
            }
            return result
        }

        fun get_colaboradoresDeProyecto(): ArrayList<vColaboradoresCompleto> {
            val result = ArrayList<vColaboradoresCompleto>()
            val colaboradores = GP_Procedures.get_ColaboradoresCompleto()
            for (e in colaboradores){
                if (e.proyecto== AC_Proyecto!!.id){
                    result.add(e)
                }
            }
            return result
        }

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