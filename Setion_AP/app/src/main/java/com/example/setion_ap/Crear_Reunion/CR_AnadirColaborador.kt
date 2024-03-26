package com.example.setion_ap.Crear_Reunion

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class CR_AnadirColaborador : AppCompatActivity() {
    //BUTTONS
    private lateinit var btnCancelar: Button
    private lateinit var btnGuardar: Button
    private lateinit var imgLupa: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cr_anadir_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnCancelar = findViewById(R.id.btnCancelar_CRANADIRCOLABORADOR)
        btnGuardar = findViewById(R.id.btnGuardar_CRANADIRCOLABORADOR)
        imgLupa = findViewById(R.id.imgLUPA_CRANADIRCOLABORADOR)
    }

    private fun initListeners() {
        btnCancelar.setOnClickListener{finish()}
        btnGuardar.setOnClickListener{finish()}
        imgLupa.setOnClickListener{}
    }
}