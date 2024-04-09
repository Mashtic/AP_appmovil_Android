package com.example.setion_ap.APP

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class PaginaPrincipal : AppCompatActivity() {
    private lateinit var btnGestionProyectos: Button
    private lateinit var btnGestionColaboradores: Button
    private lateinit var btnEvaluaciones: Button

    private lateinit var btnSalir: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paginaprincipal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnGestionProyectos = findViewById(R.id.btnGestionDeProyectos_PAGINAPRINCIPAL)
        btnGestionColaboradores = findViewById(R.id.btnGestionDeColaboradores_PAGINAPRINCIPAL)
        btnEvaluaciones = findViewById(R.id.btnEvaluaciones_PAGINAPRINCIPAL)
        btnSalir = findViewById(R.id.btnSalir_PAGINAPRINCIPAL)
    }

    private fun initListeners() {
        btnGestionProyectos.setOnClickListener { startGestionProyectosActivity() }
        btnGestionColaboradores.setOnClickListener { startGestionColaboradoresActivity() }
        btnEvaluaciones.setOnClickListener { startEvaluacionesActivity() }
        btnSalir.setOnClickListener{finish()}
    }

    private fun startGestionProyectosActivity() {
        startActivity(Intent(this, Main_GestionProyectos::class.java))
    }

    private fun startGestionColaboradoresActivity() {
        startActivity(Intent(this, Main_GestionColaboradores::class.java))
    }

    private fun startEvaluacionesActivity() {
        startActivity(Intent(this, Main_Evaluaciones::class.java))
    }

    private fun fun_atras() {
        println("Hola")
    }


}
