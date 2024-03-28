package com.example.setion_ap.APP.GestionProyectos.Consulta_De_Proyectos

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class CP_Consulta_De_Proyectos : AppCompatActivity() {
    private lateinit var btnAtras:Button
    private lateinit var imgLupa:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cp_consulta_de_proyectos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_CPCONSULTAPROYECTOS)
        imgLupa = findViewById(R.id.imgLUPA_CPCONSULTAPROYECTOS)
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
        imgLupa.setOnClickListener{}
    }
}