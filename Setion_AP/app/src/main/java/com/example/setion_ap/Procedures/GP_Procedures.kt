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

    //ASDAAASD
}