package com.example.setion_ap.Procedures

import android.content.Context
import android.widget.Toast
import java.sql.SQLException

private var connectSql = ConnectSql()

object GP_Procedures {
    fun insertarProyecto(nombre: String, presupuesto: Double, estadoDelProyecto: Int,
                         descripcion: String, fechaInicio: java.sql.Date, responsable: String, contex:Context){
        try {
            val callStatement = connectSql.dbConn()?.prepareCall("{CALL dbo.sp_InsertarProyecto(?, ?, ?, ?, ?, ?)}")!!

            callStatement.setString(1, nombre)
            callStatement.setDouble(2, presupuesto)
            callStatement.setInt(3, estadoDelProyecto)
            callStatement.setString(4, descripcion)
            callStatement.setDate(5, fechaInicio)
            callStatement.setString(6, responsable)

            callStatement.execute()
            Toast.makeText(contex, "Se ha insertado correctamente", Toast.LENGTH_LONG).show()
        } catch (ex: SQLException) {
            Toast.makeText(contex, ex.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun GET_PROYECTOS(connectSql: ConnectSql): ArrayList<String> {
        val datosPrueba = ArrayList<String>()

        try {
            val consulta = connectSql.dbConn()?.prepareStatement("SELECT nombre FROM Proyectos")
            val resultado = consulta?.executeQuery()

            while (resultado?.next() == true) {
                val nombre = resultado.getString("nombre")
                datosPrueba.add(nombre)
            }

            resultado?.close()
            consulta?.close()
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }

        return datosPrueba
    }
}