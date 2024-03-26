package com.example.setion_ap.Modificacion_De_Informacion

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class MI_EstadoDeTarea : AppCompatActivity() {
    private lateinit var btnCancelar: Button
    private lateinit var btnPorHacer: Button
    private lateinit var btnEnProgreso: Button
    private lateinit var btnFinalizado: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mi_estado_de_tarea)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnCancelar = findViewById(R.id.btnCancelarMIESTADOTAREAS)
        btnPorHacer = findViewById(R.id.btnPorHacerMIESTADOTAREAS)
        btnEnProgreso = findViewById(R.id.btnEnProgresoMIESTADOTAREAS)
        btnFinalizado = findViewById(R.id.btnFinalizadoMIESTADOTAREAS)
    }

    private fun initListeners() {
        btnCancelar.setOnClickListener{finish()}
        btnPorHacer.setOnClickListener{finish()}
        btnEnProgreso.setOnClickListener{finish()}
        btnFinalizado.setOnClickListener{finish()}
    }
}