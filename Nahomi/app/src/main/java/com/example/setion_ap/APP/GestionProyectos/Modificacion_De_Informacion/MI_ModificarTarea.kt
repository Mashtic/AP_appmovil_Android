package com.example.setion_ap.APP.GestionProyectos.Modificacion_De_Informacion

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class MI_ModificarTarea : AppCompatActivity() {
    //BUTTONS
    private lateinit var btnAtras: Button
    private lateinit var imgLupa: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mi_modificar_tarea)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_MIMODIFICARTAREAS)
        imgLupa = findViewById(R.id.imgLUPA_MIMODIFICARTAREAS)
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
        imgLupa.setOnClickListener{}
    }
}