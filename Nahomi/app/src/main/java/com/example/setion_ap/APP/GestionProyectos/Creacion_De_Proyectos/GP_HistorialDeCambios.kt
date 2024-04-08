package com.example.setion_ap.APP.GestionProyectos.Creacion_De_Proyectos

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class GP_HistorialDeCambios : AppCompatActivity() {
    private lateinit var btnAceptar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gp_historial_de_cambios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
    }
    private fun initComponents() {
        btnAceptar = findViewById(R.id.btnAceptar_GPHISTORIALCAMBIOS)
    }

    private fun initListeners() {
        btnAceptar.setOnClickListener{finish()}
    }
}