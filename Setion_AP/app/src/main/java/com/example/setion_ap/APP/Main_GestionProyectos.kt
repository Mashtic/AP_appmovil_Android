package com.example.setion_ap.APP

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.setion_ap.APP.GestionProyectos.Consulta_De_Proyectos.CP_Consulta_De_Proyectos
import com.example.setion_ap.APP.GestionProyectos.Crear_Reunion.CR_NuevaReunion
import com.example.setion_ap.APP.GestionProyectos.Modificacion_De_Informacion.MI_ModificarTarea
import com.example.setion_ap.APP.GestionProyectos.Creacion_De_Proyectos.GP_CreacionProyectos
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.NotificationService
import com.example.setion_ap.Procedures.NotificationWorker
import com.example.setion_ap.Procedures.NotifyMeetingWorker
import com.example.setion_ap.R
import java.util.concurrent.TimeUnit

class Main_GestionProyectos : AppCompatActivity() {
    private lateinit var btnCreacionDeProyectos:Button
    private lateinit var btnConsultaDeProyectos:Button
    private lateinit var btnModificacionDeInformacion:Button
    private lateinit var btnCreacionDeReuniones:Button
    private lateinit var btnAtras:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        // Se realiza para revisar si hay notificaciones pendientes por implementar, especialmente
        // sobre tareas.
        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>().
        setInitialDelay(15, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this).enqueue(notificationWorkRequest)

        // Se realiza para revisar si hay notificaciones pendientes por implementar, especialmente
        // sobre reuniones.
        val periodicWorkRequest = OneTimeWorkRequestBuilder<NotifyMeetingWorker>().
        setInitialDelay(15, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(this).enqueue(periodicWorkRequest)

        //val notificationService = NotificationService()
        //notificationService.notifyMeetingParticipants()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_gestionproyectos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val list = GP_Procedures.get_colaboradores_libres()

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnCreacionDeProyectos = findViewById<Button>(R.id.btnCreacionDeProyectos_MAINGP)
        btnConsultaDeProyectos = findViewById<Button>(R.id.btnConsultaDeProyectos_MAINGP)
        btnModificacionDeInformacion= findViewById<Button>(R.id.btnModificacionDeInformacion_MAINGP)
        btnCreacionDeReuniones= findViewById<Button>(R.id.btnCreacionDeReuniones_MAINGP)
        btnAtras = findViewById<Button>(R.id.btnAtras_MAINGP)
    }

    private fun initListeners() {
        btnCreacionDeProyectos.setOnClickListener{fun_crecionDeProyectos()}
        btnConsultaDeProyectos.setOnClickListener{fun_consultaDeProyectos()}
        btnModificacionDeInformacion.setOnClickListener{fun_modificacionDeInformacion()}
        btnCreacionDeReuniones.setOnClickListener{fun_crecionDeReuniones()}
        btnAtras.setOnClickListener{fun_atras()}
    }

    private fun fun_crecionDeProyectos() {
        intent = Intent(this, GP_CreacionProyectos::class.java)
        startActivity(intent)
    }

    private fun fun_consultaDeProyectos() {
        intent = Intent(this, CP_Consulta_De_Proyectos::class.java)
        startActivity(intent)
    }

    private fun fun_modificacionDeInformacion() {
        intent = Intent(this, MI_ModificarTarea::class.java)
        startActivity(intent)
    }

    private fun fun_crecionDeReuniones() {
        intent = Intent(this, CR_NuevaReunion::class.java)
        startActivity(intent)
    }

    private fun fun_atras() {
        finish()
    }
}