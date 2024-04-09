package com.example.setion_ap.APP

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.GestionColaboradores.Asignaciones.AsignarProyecto
import com.example.setion_ap.APP.GestionColaboradores.Modificaciones.ModificacionInfo
import com.example.setion_ap.APP.GestionColaboradores.Registro.RegistroColaborador
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R

class Main_GestionColaboradores : AppCompatActivity() {
    private lateinit var btnRegistro:Button
    private lateinit var btnAsignacion:Button
    private lateinit var btnModificacionInformacion:Button
    private lateinit var btnAtras:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_gestion_colaboradores)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnRegistro = findViewById(R.id.btnRegistro_MAINGC)
        btnAsignacion= findViewById(R.id.btnAsignacion_MAINGC)
        btnModificacionInformacion= findViewById(R.id.btnModificacionDeInformacion_MAINGC)
        btnAtras = findViewById(R.id.btnAtras_MAINGC)
    }

    private fun initListeners() {
        btnRegistro.setOnClickListener{fun_registro()}
        btnAsignacion.setOnClickListener{fun_asignacion()}
        btnModificacionInformacion.setOnClickListener{fun_MInformacion()}
        btnAtras.setOnClickListener{ finish() }
    }

    private fun fun_registro() {
        startActivity(Intent(this, RegistroColaborador::class.java))
    }

    private fun fun_asignacion() {
        startActivity(Intent(this, AsignarProyecto::class.java))
    }

    private fun fun_MInformacion() {
        startActivity(Intent(this, ModificacionInfo::class.java))
    }
}