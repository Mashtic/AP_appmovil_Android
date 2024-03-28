package com.example.setion_ap.APP.GestionProyectos.Crear_Reunion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class CR_NuevaReunion : AppCompatActivity() {
    //BUTTONS
    private lateinit var btnCancelar: Button
    private lateinit var btnAgendar: Button
    private lateinit var btnAnadir: Button
    private lateinit var imgLupa: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cr_nueva_reunion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnCancelar = findViewById(R.id.btnCancelar_CRCREARREUNION)
        btnAgendar = findViewById(R.id.btnAgendar_CRCREARREUNION)
        btnAnadir = findViewById(R.id.btnAnadir_CRCREARREUNION)
        imgLupa = findViewById(R.id.imgLUPA_CRCREARREUNION)
    }

    private fun initListeners() {
        btnCancelar.setOnClickListener{finish()}
        btnAgendar.setOnClickListener{finish()}
        btnAnadir.setOnClickListener{fun_AnadirReunion()}
        imgLupa.setOnClickListener{}
    }

    private fun fun_AnadirReunion() {
        intent = Intent(this, CR_CorreoInvitacion::class.java)
        startActivity(intent)
    }
}