package com.example.setion_ap

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.pantallas_gestionProyectos.Creacion_De_Proyectos.GP_CreacionProyectos

class Main_GestionProyectos : AppCompatActivity() {
    private lateinit var btnCreacionDeProyectos:Button
    private lateinit var btnConsultaDeProyectos:Button
    private lateinit var btnModificacionDeInformacion:Button
    private lateinit var btnCreacionDeReuniones:Button
    private lateinit var btnAtras:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_gestionproyectos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initListeners() {
        btnCreacionDeProyectos.setOnClickListener{fun_crecionDeProyectos()}
        btnConsultaDeProyectos.setOnClickListener{fun_consultaDeProyectos()}
        btnModificacionDeInformacion.setOnClickListener{fun_modificacionDeInformacion()}
        btnCreacionDeReuniones.setOnClickListener{fun_crecionDeReuniones()}
        btnAtras.setOnClickListener{fun_atras()}
    }

    private fun initComponents() {
        btnCreacionDeProyectos = findViewById<Button>(R.id.btnCreacionDeProyectos)
        btnConsultaDeProyectos = findViewById<Button>(R.id.btnConsultaDeProyectos)
        btnModificacionDeInformacion= findViewById<Button>(R.id.btnModificacionDeInformacion)
        btnCreacionDeReuniones= findViewById<Button>(R.id.btnCreacionDeReuniones)
        btnAtras = findViewById<Button>(R.id.btnAtras)
    }

    private fun fun_crecionDeProyectos() {
        intent = Intent(this, GP_CreacionProyectos::class.java)
        startActivity(intent)
    }

    private fun fun_crecionDeReuniones() {
        println("Hola")
    }

    private fun fun_modificacionDeInformacion() {
        println("Hola")
    }

    private fun fun_consultaDeProyectos() {
        println("Hola")
    }

    private fun fun_atras() {
        println("Hola")
    }


}