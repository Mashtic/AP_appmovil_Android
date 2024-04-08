package com.example.setion_ap.Procedures

import android.content.Context
import androidx.work.WorkerParameters
import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import androidx.work.CoroutineWorker


class NotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val notificationService = NotificationService()
        notificationService.checkAndNotify()

        // Indica que el trabajo se completó exitosamente
        return Result.success()
    }
}

class NotifyMeetingWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val notificationService = NotificationService()
        notificationService.notifyMeetingParticipants()

        return Result.success()
    }
}


object EmailUtils {
    fun sendEmail(recipients: List<String?>, subject: String, content: String) {
        val session = Session.getInstance(emailProperties(), object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication("proyectoap26@gmail.com", "vshikiqjajqoaolh")
            }
        })

        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress("proyectoap26@gmail.com"))
                this.subject = subject
                setText(content)
                recipients.forEach { addRecipient(Message.RecipientType.TO, InternetAddress(it)) }
            }

            Transport.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }

    private fun emailProperties(): Properties {
        return Properties().apply {
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "465")
            put("mail.smtp.auth", "true")
            //put("mail.smtp.starttls.enable", "true")
            put("mail.smtp.ssl.enable", "true")
        }
    }
}



