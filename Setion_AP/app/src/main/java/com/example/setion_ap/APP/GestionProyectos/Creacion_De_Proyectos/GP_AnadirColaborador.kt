package com.example.setion_ap.APP.GestionProyectos.Creacion_De_Proyectos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class GP_AnadirColaborador : AppCompatActivity() {
    private lateinit var btnCancelar: Button
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gp_anadir_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnCancelar = findViewById(R.id.btnCancelar_GPANADIRCOLABORADOR)
        btnGuardar = findViewById(R.id.btnGuardar_GPANADIRCOLABORADOR)
    }

    private fun initListeners() {
        btnGuardar.setOnClickListener{finish()}
        btnCancelar.setOnClickListener{finish()}
    }
}