package com.example.setion_ap.Procedures

import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Time
import java.util.Date

class ConnectSql {
    private val ip = "192.168.0.19:1433"//"192.168.100.23:1433"//192.168.100.6:1433
    private val db = "GestorProyectos"
    private val username = "jei"
    private val password = "12345"

    fun dbConn(): Connection? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn: Connection? = null
        val connString: String
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance()
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password"
            conn = DriverManager.getConnection(connString)
        } catch (ex: SQLException) {
            Log.e("Error", ex.message!!)
        } catch (ex1: ClassNotFoundException) {
            Log.e("Error", ex1.message!!)
        } catch (ex2: Exception) {
            Log.e("Error", ex2.message!!)
        }
        return conn
    }
}

/**
 * Clase que define los colaboradores libres
 */
data class vColaboradores(
    val cedula: String,
    val nombreCompleto: String,
    val correoElectronico: String,
    val departamento: String,
    val telefono: String,
    val proyecto: String
)

/**
 * Clase que define las reuniones
 */
data class vReuniones(
    val idReunion: Int = 1,
    val nombre: String,
    val fecha: Date,
    val hora: Time,
    val temaReunion: String,
    val medioReunion: String,
)

/**
 * Clase que define los participantes de las reuniones
 */
data class vParticipantesReuniones(
    val idReunion: Int,
    val temaR: String, // tema de la reunión
    val nombrePart: String, // nombre del participante
)

/**
 * Clase que define las tareas por proyecto
 */
data class vProyectoTareas(
    val idProyecto: Int,
    val nombrePry: String,
    val tareaId: Int,
    val descripcion: String, // tema de la reunión
    val storyPoint: Int,
    val nombEstadoTarea: String,
    val nombEnc: String
)




/**
 * Clase que define los proyectos
 */
data class vProyectos(
    val id: Int,
    val nombrePry: String,
    val presupuesto: Double,
    val estadoPry: String, //
    val descripcion: String, //
    val fechaInicio: String, //
    val nombRespons: String, // nombre del responsable
    val recursosNecesarios: String
)

/**
 * Clase que define como agregar un colaborador a un proyecto
 */
data class aColaboradorPry(
    val cedula: Int,
    val pryId: Int
)


/**
 * Clase que define como agregar un colaborador a un proyecto
 */
data class aTareaPry(
    val proyectoID: Int,
    val descripcion: String,
    val storyPoint: Int,
    val estadoTarea: Int,
    val encargado: String
)



/**
 * Clase que define las propiedades necesarias
 */
data class uModificarTarea(
    val idProyecto: Int,
    val tareaId: Int,
    val descripcion: String,
    val storyPoint: Int, // tema de la reunión
    val estadoTarea: Int,
    val encargado: String
)


/**
 *  * Clase que define las propiedades necesarias para mostrar las tareas POR EL ESTADO DE LA TAREA
 *  Y POR EL ENCARGADO.
 */
data class vTareasPorEstadoYEncargado(
    val idProyecto: Int,
    val nombrePry: String,
    val tareaId: Int,
    val descripcion: String,
    val storyPoint: Int, // tema de la reunión
    val estadoTarea: String,
    val encargado: String
)

/**
 * Clase para definir los parametros necesarios para modificar un colaborador
 */
data class uColaborador(
    val cedula: String,
    val email: String,
    val telefono: String,
    val departamento: Int
)