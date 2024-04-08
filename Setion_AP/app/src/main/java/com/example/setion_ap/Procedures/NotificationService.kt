package com.example.setion_ap.Procedures

import java.sql.Date
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Time


class NotificationService {
    fun checkAndNotify() {
        println("Estoy escuchando.")
        val pendingEvents = getPendingEventsFromDatabase()
        pendingEvents.forEach { evento ->
            val emails = getEmailsForProject(evento.proyectoID)
            if (emails.isNotEmpty()) {
                EmailUtils.sendEmail(
                    emails,
                    "Notificación del Proyecto ${evento.proyectoID}",
                    evento.descripcionEvento
                )
                markEventAsNotified(evento.id)
            }
        }
    }

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

    fun notifyMeetingParticipants() {
        val eventsToNotify = getMeetingEventsFromDatabase()

        eventsToNotify.forEach { event ->

            val email = getEmailForParticipant(event.participante.toString())
            if (email != null) {
                val meetingDetails = getMeetingDetails(event.reunionID)
                if (meetingDetails != null) {
                    EmailUtils.sendEmail(
                        listOf(email),
                        "Invitación a la reunión: ${meetingDetails.temaReunion}",
                        "Estimado colaborador, está invitado a una reunión sobre ${meetingDetails.temaReunion} que se realizará el ${meetingDetails.fecha} a las ${meetingDetails.hora} por ${meetingDetails.medioReunion}."
                    )
                }
                markEventAsNotified(event.id)
            }
        }
    }

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

    /**
     * ---------------------------------------------------------------------------------------------
     */
/*
    fun notifyMeetingParticipants() {
        val statement = ConnectSql().dbConn()?.prepareStatement("""
                SELECT rp.reunionID, rp.participante, r.fecha, r.hora, r.temaReunion, r.medioReunion
                FROM ReunionesParticipantes rp
                JOIN Reuniones r ON rp.reunionID = r.id
                WHERE NOT EXISTS (
                    SELECT 1 FROM EventosParaNotificar e
                    WHERE e.reunionID = rp.reunionID AND e.participante = rp.participante
                )
            """.trimIndent())

        val resultSet = statement?.executeQuery()
        if (resultSet != null) {
            while (resultSet.next()) {
                val reunionDetails = extractReunionDetails(resultSet)
                val participantEmail = getEmailForParticipant(resultSet.getString("participante"))
                val message = buildEmailMessage(reunionDetails)

                EmailUtils.sendEmail(listOf(participantEmail), "Invitación a la reunión: ${reunionDetails.temaReunion}", message)

                insertNotificationEvent(reunionDetails, resultSet.getString("participante"))
            }
        }

        if (resultSet != null) {
            resultSet.close()
        }
        if (statement != null) {
            statement.close()
        }
        ConnectSql().dbConn()?.close()
    }

    private fun getEmailForParticipant(participante: String): String? {
        val query = "SELECT email FROM Colaboradores WHERE cedula = ?"
        val statement = ConnectSql().dbConn()?.prepareStatement(query)

        statement?.setString(1, participante)

        var email: String? = null
        val resultSet = statement?.executeQuery()
        if (resultSet != null) {
            if (resultSet.next()) {
                email = resultSet.getString("email")
            } else {
                println("No se encontró un email para el participante con cédula: $participante")
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

    private fun extractReunionDetails(resultSet: ResultSet): ReunionDetails {
        return ReunionDetails(
            reunionID = resultSet.getInt("reunionID"),
            fecha = resultSet.getDate("fecha"),
            hora = resultSet.getTime("hora"),
            temaReunion = resultSet.getString("temaReunion"),
            medioReunion = resultSet.getString("medioReunion")
        )
    }

    private fun buildEmailMessage(reunionDetails: ReunionDetails): String {
        return "Detalles de la reunión: Fecha: ${reunionDetails.fecha}, Hora: ${reunionDetails.hora}, Tema: ${reunionDetails.temaReunion}, Medio: ${reunionDetails.medioReunion}."
    }
    private fun insertNotificationEvent(reunionDetails: ReunionDetails, participante: String) {
        val statement = ConnectSql().dbConn()?.prepareStatement("""
            INSERT INTO EventosParaNotificar (tipoEvento, reunionID, participante, descripcionEvento)
            VALUES ('Invitación a Reunión', ?, ?, ?)
        """.trimIndent())

        if (statement != null) {
            statement.setInt(1, reunionDetails.reunionID)
        }
        if (statement != null) {
            statement.setString(2, participante)
        }
        if (statement != null) {
            statement.setString(3, buildEmailMessage(reunionDetails))
        }

        if (statement != null) {
            statement.executeUpdate()
        }
        if (statement != null) {
            statement.close()
        }
    } */
}


data class Reunion(
    val id: Int,
    val fecha: Date,
    val hora: Time,
    val temaReunion: String,
    val medioReunion: String
)

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