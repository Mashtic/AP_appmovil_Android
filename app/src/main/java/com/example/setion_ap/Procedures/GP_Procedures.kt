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

    /**
     * @param:
     * @return: Una lista con los colaboradores que se encuentran libres, es decir, sin proyecto
     * asignado.
     */
    fun get_colaboradores_libres( ): List<vColaboradores> {
        val colaboradoresLibres = mutableListOf<vColaboradores>()

        try {
            // Preparar la llamada al stored procedure
            val statement = connectSql.dbConn()?.prepareCall("{call MostrarColaboradoresLibres}")

            // Ejecutar el stored procedure
            val resultSet = statement?.executeQuery()

            // Procesar los resultados
            if (resultSet != null) {
                while (resultSet.next()!! == true) {
                    val colaboradorLibre = vColaboradores(
                        cedula = resultSet.getString("Cédula"),
                        nombreCompleto = resultSet.getString("Nombre completo"),
                        correoElectronico = resultSet.getString("Correo electrónico"),
                        departamento = resultSet.getString("Departamento"),
                        telefono = resultSet.getString("Teléfono"),
                        proyecto = resultSet.getString("Proyecto")
                    )
                    colaboradoresLibres.add(colaboradorLibre)
                }
            }
        } catch (e1: SQLException) {
            println("Error:::$e1")
        } finally {
            // Asegurarse de cerrar la conexión
            try {
                connectSql.dbConn()?.close()
            } catch (e2: SQLException) {
                // Manejo de error al cerrar conexión
                println("Error:::$e2")
            }
        }
    return colaboradoresLibres
    }

    /**
     * @param: el identificador del proyecto
     * @return: Una lista con los colaboradores que se encuentran trabajando en un proyecto x.
     */
    fun get_colaboradores_por_proyecto(proyectoId: Int): List<vColaboradores> {
        val colaboradores_por_proyecto = mutableListOf<vColaboradores>()

        try {
            // Preparar la llamada al stored procedure
            val callableStatement = connectSql.dbConn()?.prepareCall("{call MostrarColaboradoresXProyecto(?)}")
            callableStatement?.setInt(1, proyectoId)

            // Ejecutar el stored procedure
            val resultSet = callableStatement?.executeQuery()

            // Procesar los resultados
            if (resultSet != null) {
                while (resultSet.next()!! == true) {
                    val colaborador_por_proyecto = vColaboradores(
                        cedula = resultSet.getString("Cédula"),
                        nombreCompleto = resultSet.getString("Nombre completo"),
                        correoElectronico = resultSet.getString("Correo electrónico"),
                        departamento = resultSet.getString("Departamento"),
                        telefono = resultSet.getString("Teléfono"),
                        proyecto = resultSet.getString("Proyecto")
                    )
                    colaboradores_por_proyecto.add(colaborador_por_proyecto)
                }
            }
        } catch (e1: SQLException) {

            println("Error:::$e1")
        } finally {
            // Asegurarse de cerrar la conexión
            try {
                connectSql.dbConn()?.close()
            } catch (e2: SQLException) {
                // Manejo de error al cerrar conexión
                println("Error:::$e2")
            }
        }
        return colaboradores_por_proyecto
    }

    /**
     * @param:
     * @return: Una lista con los colaboradores.
     */
    fun get_colaboradores ( ): List<vColaboradores> {
        val colaboradores = mutableListOf<vColaboradores>()

        try {
            // Preparar la llamada al stored procedure
            val statement = connectSql.dbConn()?.prepareCall("{call MostrarInfoColaboradores}")

            // Ejecutar el stored procedure
            val resultSet = statement?.executeQuery()

            // Procesar los resultados
            if (resultSet != null) {
                while (resultSet.next()!! == true) {
                    val colaborador = vColaboradores(
                        cedula = resultSet.getString("Cédula"),
                        nombreCompleto = resultSet.getString("Nombre completo"),
                        correoElectronico = resultSet.getString("Correo electrónico"),
                        departamento = resultSet.getString("Departamento"),
                        telefono = resultSet.getString("Teléfono"),
                        proyecto = resultSet.getString("Proyecto")
                    )
                    colaboradores.add(colaborador)
                }
            }
        } catch (e1: SQLException) {
            println("Error:::$e1")
        } finally {
            // Asegurarse de cerrar la conexión
            try {
                connectSql.dbConn()?.close()
            } catch (e2: SQLException) {
                // Manejo de error al cerrar conexión
                println("Error:::$e2")
            }
        }
        return colaboradores
    }

    /**
     * @param:
     * @return: Una lista con las reuniones.
     * asignado.
     */
    fun get_reuniones( ): List<vReuniones> {
        val reuniones = mutableListOf<vReuniones>()

        try {
            // Preparar la llamada al stored procedure
            val statement = connectSql.dbConn()?.prepareCall("{call MostrarInfoReuniones}")

            // Ejecutar el stored procedure
            val resultSet = statement?.executeQuery()

            // Procesar los resultados
            if (resultSet != null) {
                while (resultSet.next()!! == true) {
                    val reunion = vReuniones(
                        nombre = resultSet.getString("Nombre proyecto"),
                        fecha = resultSet.getDate("Fecha reunión"),
                        hora = resultSet.getTime("Hora reunión"),
                        temaReunion = resultSet.getString("Tema reunión"),
                        medioReunion = resultSet.getString("Medio reunión")
                    )
                    reuniones.add(reunion)
                }
            }
        } catch (e1: SQLException) {
            println("Error:::$e1")
        } finally {
            // Asegurarse de cerrar la conexión
            try {
                connectSql.dbConn()?.close()
            } catch (e2: SQLException) {
                // Manejo de error al cerrar conexión
                println("Error:::$e2")
            }
        }
        return reuniones
    }

    /**
     * @param:
     * @return: Una lista con los participantes de las reuniones.
     * asignado.
     */
    fun get_part_reuniones( ): List<vParticipantesReuniones> {
        val partReuniones = mutableListOf<vParticipantesReuniones>()

        try {
            // Preparar la llamada al stored procedure
            val statement = connectSql.dbConn()?.prepareCall("{call MostrarInfoParticipantesReuniones}")

            // Ejecutar el stored procedure
            val resultSet = statement?.executeQuery()

            // Procesar los resultados
            if (resultSet != null) {
                while (resultSet.next()!! == true) {
                    val participante = vParticipantesReuniones(
                        idReunion = resultSet.getInt("Id reunión"),
                        temaR = resultSet.getString("Tema reunión"),
                        nombrePart = resultSet.getString("Nombre participante")
                    )
                    partReuniones.add(participante)
                }
            }
        } catch (e1: SQLException) {
            println("Error:::$e1")
        } finally {
            // Asegurarse de cerrar la conexión
            try {
                connectSql.dbConn()?.close()
            } catch (e2: SQLException) {
                // Manejo de error al cerrar conexión
                println("Error:::$e2")
            }
        }
        return partReuniones
    }

    /**
     * @param:
     * @return: Una lista con las tareas de los proyectos.
     * asignado.
     */
    fun get_ProyectoTareas( ): List<vProyectoTareas> {
        val tareasProyectos = mutableListOf<vProyectoTareas>()

        try {
            // Preparar la llamada al stored procedure
            val statement = connectSql.dbConn()?.prepareCall("{call MostrarInfoProyectoTareas}")

            // Ejecutar el stored procedure
            val resultSet = statement?.executeQuery()

            // Procesar los resultados
            if (resultSet != null) {
                while (resultSet.next()!! == true) {
                    val tareaPry = vProyectoTareas(
                        idProyecto = resultSet.getInt("Proyecto id"),
                        nombrePry = resultSet.getString("Nombre proyecto"),
                        descripcion = resultSet.getString("Descripción"),
                        storyPoint = resultSet.getInt("Story Point"),
                        nombEstadoTarea = resultSet.getString("Estado de la tarea"),
                        nombEnc = resultSet.getString("Encargado"),
                    )
                    tareasProyectos.add(tareaPry)
                }
            }
        } catch (e1: SQLException) {
            println("Error:::$e1")
        } finally {
            // Asegurarse de cerrar la conexión
            try {
                connectSql.dbConn()?.close()
            } catch (e2: SQLException) {
                // Manejo de error al cerrar conexión
                println("Error:::$e2")
            }
        }
        return tareasProyectos
    }

    /**
     * @param:
     * @return: Una lista con las tareas de los proyectos.
     * asignado.
     */
    fun get_Proyectos( ): List<vProyectos> {
        val proyectos = mutableListOf<vProyectos>()

        try {
            // Preparar la llamada al stored procedure
            val statement = connectSql.dbConn()?.prepareCall("{call MostrarInformacionProyectos}")

            // Ejecutar el stored procedure
            val resultSet = statement?.executeQuery()

            // Procesar los resultados
            if (resultSet != null) {
                while (resultSet.next()!! == true) {
                    val proyecto = vProyectos(
                        id = resultSet.getInt("Id"),
                        nombrePry = resultSet.getString("Nombre"),
                        presupuesto = resultSet.getDouble("Presupuesto"),
                        estadoPry = resultSet.getString("Estado del proyecto"),
                        descripcion = resultSet.getString("Descripción"),
                        fechaInicio = resultSet.getString("Fecha inicio"),
                        nombRespons = resultSet.getString("Responsable"),
                        recursosNecesarios = resultSet.getString("Recursos necesarios"),
                    )
                    proyectos.add(proyecto)
                }
            }
        } catch (e1: SQLException) {
            println("Error:::$e1")
        } finally {
            // Asegurarse de cerrar la conexión
            try {
                connectSql.dbConn()?.close()
            } catch (e2: SQLException) {
                // Manejo de error al cerrar conexión
                println("Error:::$e2")
            }
        }
        return proyectos
    }

    /**
     * @param: cedula del colaborador, el identificador del proyecto
     * @return: Agrega el colaborador al proyecto.
     */
    fun set_colaboradorProyecto(cedula: Int, pryId: Int){
        try {
            val callStatement = connectSql.dbConn()?.prepareCall("{CALL sp_AsignarColaboradorAProyecto(?, ?)}")

            callStatement?.setInt(1, cedula)
            callStatement?.setInt(2, pryId)
            callStatement!!?.execute()
            println("Se ha asignado un colaborador al proyecto.")

            //            Toast.makeText(contex, "Se ha insertado correctamente", Toast.LENGTH_LONG).show()
        } catch (ex: SQLException) {
            println("Error: $ex")
            //Toast.makeText(contex, ex.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * @param: id del proyecto, descripcion de la tarea, un story point, un estado de la tarea, un
     * encargado
     * @return: Agrega una tarea al proyecto
     */
    fun set_tareaProyecto(pryId: Int, descripcion: String, storyPoint: Int,
                          estadoTarea: Int, encargado: String){
        try {
            val callStatement = connectSql.dbConn()?.prepareCall("{CALL sp_InsertarTareaAProyecto(?, ?, ?, ?, ?)}")

            callStatement?.setInt(1, pryId)
            callStatement?.setString(2, descripcion)
            callStatement?.setInt(3, storyPoint)
            callStatement?.setInt(4, estadoTarea)
            callStatement?.setString(5, encargado)
            callStatement!!.execute()
            println("Se ha agregado una tarea al proyecto.")

            //            Toast.makeText(contex, "Se ha insertado correctamente", Toast.LENGTH_LONG).show()
        } catch (ex: SQLException) {
            println("Error: $ex")
            //Toast.makeText(contex, ex.message.toString(), Toast.LENGTH_LONG).show()
        }
    }


}


/**
 * dbo.MostrarColaboradoresXProyecto ---
 * dbo.MostrarInfoColaboradores ---
 * dbo.MostrarInfoParticipantesReuniones ---
 * dbo.MostrarInfoProyectoTareas ---
 * dbo.MostrarInfoProyectos ---
 * dbo.MostrarInfoReuniones ---
 * dbo.MostrarInformacionProyectos ---
 * dbo.sp_AgregarMensajeAForo
 * dbo.sp_AgregarParticipanteAReunion
 * dbo.sp_AsignarColaboradorAProyecto ---
 * dbo.sp_CrearForo
 * dbo.sp_CrearReunion
 * dbo.sp_EliminarColaboradorDeProyecto
 * dbo.sp_InsertarColaborador
 * dbo.sp_InsertarProyecto ---
 * dbo.sp_InsertarRecursoAProyecto ---
 * dbo.sp_InsertarTareaAProyecto ---
 * dbo.sp_ModificarColaborador
 * dbo.sp_ModificarTarea
 * dbo.sp_ObtenerTareasPorEstadoYEncargado
 * dbo.sp_ReasignarColaboradorAProyecto
 * dbo.sp_VerificarCredenciales
 *
 */