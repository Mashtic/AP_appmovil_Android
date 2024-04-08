package com.example.setion_ap.Procedures

import java.sql.Date
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Time


/**
 * Clase para encargarse de notificar.
 */
class NotificationService {

    /**
     * Esta funcion es la principal para enviar correos sobre las tareas de los proyectos
     */
    fun checkAndNotify() {
        val pendingEvents = getPendingEventsFromDatabase() // Obtiene las notif pendientes en la BD
        pendingEvents.forEach { evento ->
            val emails = getEmailsForProject(evento.proyectoID)  //Obtiene los correos de los colab
            if (emails.isNotEmpty()) {
                EmailUtils.sendEmail( //Llama la función de enviar email y pasa parametros
                    emails, // Los correos
                    "Notificación del Proyecto ${evento.proyectoID}", // El tema del correo
                    evento.descripcionEvento // Descripción
                )
                markEventAsNotified(evento.id) // Se setea en la base de datos que ya se ha notificado
            }
        }
    }

    /**
     * Obtiene los eventos que no se han notificado
     */
    private fun getPendingEventsFromDatabase(): List<Evento> {
        val eventos = mutableListOf<Evento>()

        val statement = ConnectSql().dbConn()?.prepareStatement("SELECT * FROM EventosParaNotificar WHERE notificado = 0")
        val resultSet = statement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                eventos.add(Evento.fromResultSet(resultSet))
            }
        }

        if (resultSet != null) {
            resultSet.close()
        }
        if (statement != null) {
            statement.close()
        }
        ConnectSql().dbConn()?.close()

        return eventos
    }

    /**
     * Obtiene los emails de los colaboradores del proyecto por notificar
     * @param: El id del proyecto
     * @return: Una lista con los correos
     */
    private fun getEmailsForProject(proyectoID: Int?): List<String> {
        val emails = mutableListOf<String>()
        val statement = ConnectSql().dbConn()?.prepareStatement("SELECT email FROM Colaboradores WHERE proyecto = ?")

        if (statement != null) {
            if (proyectoID != null) {
                statement.setInt(1, proyectoID)
            }
        }

        val resultSet = statement?.executeQuery()
        if (resultSet != null) {
            while (resultSet.next()) {
                emails.add(resultSet.getString("email"))
            }
        }

        if (resultSet != null) {
            resultSet.close()
        }
        if (statement != null) {
            statement.close()
        }
        ConnectSql().dbConn()?.close()

        return emails
    }

    /**
     * Actualiza los eventos procesados como notificados.
     * @param: El id del evento
     * @return: Null
     */
    private fun markEventAsNotified(eventoID: Int) {
        val statement = ConnectSql().dbConn()?.prepareStatement("UPDATE EventosParaNotificar SET notificado = 1 WHERE id = ?")

        if (statement != null) {
            statement.setInt(1, eventoID)
        }

        if (statement != null) {
            statement.executeUpdate()
        }

        if (statement != null) {
            statement.close()
        }
        ConnectSql().dbConn()?.close()
    }


    /**
     * Esta funcion es la principal para enviar correos sobre las reuniones.
     */
    fun notifyMeetingParticipants() {
        val eventsToNotify = getMeetingEventsFromDatabase() // eventos por notificar
        //Itera sobre cada uno
        eventsToNotify.forEach { event ->
            val email = getEmailForParticipant(event.participante.toString()) //Obtiene el email del colaborador
            if (email != null) {
                val meetingDetails = getMeetingDetails(event.reunionID) //Obtiene los detalles de la reunión
                if (meetingDetails != null) {
                    EmailUtils.sendEmail( //Se llama la función sendEmail con sus parametros
                        listOf(email),
                        "Invitación a la reunión: ${meetingDetails.temaReunion}",
                        "Estimado colaborador, \n\nHa sido invitado a una reunión acerca de: ${meetingDetails.temaReunion} \nFecha: ${meetingDetails.fecha} \nHora: ${meetingDetails.hora} \nMedio: ${meetingDetails.medioReunion}."
                    )
                }
                markEventAsNotified(event.id)
            }
        }
    }

    /**
     * Función para obtener los eventos de las reuniones
     */
    private fun getMeetingEventsFromDatabase(): List<Evento> {
        val eventos = mutableListOf<Evento>()
        val statement = ConnectSql().dbConn()?.prepareStatement("""
            SELECT * FROM EventosParaNotificar 
            WHERE notificado = 0 AND tipoEvento = 'Invitación a Reunión'
        """.trimIndent())

        val resultSet = statement?.executeQuery()
        if (resultSet != null) {
            while (resultSet.next()) {
                eventos.add(
                    Evento(
                        id = resultSet.getInt("id"),
                        tipoEvento = resultSet.getString("tipoEvento"),
                        proyectoID = resultSet.getInt("proyectoID"),
                        reunionID = resultSet.getInt("reunionID"),
                        participante = resultSet.getString("participante"),
                        descripcionEvento = resultSet.getString("descripcionEvento")
                    )
                )
            }
        }

        if (resultSet != null) {
            resultSet.close()
        }
        if (statement != null) {
            statement.close()
        }
        ConnectSql().dbConn()?.close()

        return eventos
    }

    /**
     * Obtiene los emails de los participantes
     * (Función repetida)
     */
    private fun getEmailForParticipant(participante: String): String? {
        val statement = ConnectSql().dbConn()?.prepareStatement("SELECT email FROM Colaboradores WHERE cedula = ?")
        if (statement != null) {
            statement.setString(1, participante)
        }

        var email: String? = null
        val resultSet = statement?.executeQuery()
        if (resultSet != null) {
            if (resultSet.next()) {
                email = resultSet.getString("email")
            }
        }

        if (resultSet != null) {
            resultSet.close()
        }
        if (statement != null) {
            statement.close()
        }
        ConnectSql().dbConn()?.close()

        return email
    }

    /**
     * Obtiene los detalles de las reuniones
     */
    private fun getMeetingDetails(reunionID: Int?): Reunion? {
        val statement = ConnectSql().dbConn()?.prepareStatement("SELECT * FROM Reuniones WHERE id = ?")
        if (reunionID != null) {
            statement?.setInt(1, reunionID)
        }

        val resultSet = statement?.executeQuery()
        if (resultSet != null) {
            if (!resultSet.next()) {
                throw SQLException("No se encontró la reunión con ID: $reunionID")
            }
        }

        val reunion = resultSet?.let {
            Reunion(
                id = it.getInt("id"),
                fecha = resultSet.getDate("fecha"),
                hora = resultSet.getTime("hora"),
                temaReunion = resultSet.getString("temaReunion"),
                medioReunion = resultSet.getString("medioReunion")
            )
        }

        if (resultSet != null) {
            resultSet.close()
        }
        if (statement != null) {
            statement.close()
        }
        ConnectSql().dbConn()!!.close()

        return reunion
    }
}

/**
 * Estructura de la reunión
 */
data class Reunion(
    val id: Int,
    val fecha: Date,
    val hora: Time,
    val temaReunion: String,
    val medioReunion: String
)

/**
 * Estructura del evento
 */
data class Evento(
    val id: Int,
    val tipoEvento: String,
    val proyectoID: Int?,
    val reunionID: Int?,
    val participante: String?,
    val descripcionEvento: String
) {
    companion object {
        fun fromResultSet(resultSet: ResultSet): Evento {
            return Evento(
                id = resultSet.getInt("id"),
                tipoEvento = resultSet.getString("tipoEvento"),
                proyectoID = resultSet.getInt("proyectoID"),
                reunionID = resultSet.getInt("reunionID"),
                participante = resultSet.getString("participante"),
                descripcionEvento = resultSet.getString("descripcionEvento")
            )
        }
    }
}