package com.example.setion_ap.pantallas_gestionProyectos.Creacion_De_Proyectos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Main_GestionProyectos
import com.example.setion_ap.R

class GP_CreacionProyectos : AppCompatActivity() {
    private lateinit var btnCancelar: Button
    private lateinit var btnGuardar: Button
    private lateinit var btnHistorialDeCambios: Button
    private lateinit var btnAnadirTarea: Button
    private lateinit var imgLupa: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gp_creacion_proyectos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        //BUTTONS
        btnHistorialDeCambios=findViewById(R.id.btnHistorialDeCambios_GPCREACIONPROYECTOS)
        btnGuardar=findViewById(R.id.btnGuardar_GPCREACIONPROYECTOS)
        btnCancelar=findViewById(R.id.btnCancelar_GPCREACIONPROYECTOS)
        btnAnadirTarea=findViewById(R.id.btnAnadirTarea_GPCREACIONPROYECTOS)

        //IMAGES
        imgLupa=findViewById(R.id.imgLUPA_GPCREACIONPROYECTOS)
    }

    private fun initListeners() {
        //BOTONES
        btnCancelar.setOnClickListener { (finish()) }
        btnGuardar.setOnClickListener { fun_GuardarDatos() }
        btnHistorialDeCambios.setOnClickListener { fun_HistorialDeCambios() }
        println("ANTES DE ")
        btnAnadirTarea.setOnClickListener { fun_AnadirTarea() }
        println("Despues de")

        //IMAGENES
        imgLupa.setOnClickListener { fun_AnadirColaborador() }
    }

    private fun fun_AnadirColaborador() {
        intent = Intent(this, GP_AnadirColaborador::class.java)
        startActivity(intent)
    }

    private fun fun_GuardarDatos() {
        /*Codigo para guardar los datos*/
        finish()
    }

    private fun fun_HistorialDeCambios() {
        intent = Intent(this, GP_HistorialDeCambios::class.java)
        startActivity(intent)
    }

    private fun fun_AnadirTarea() {
        println("Entramos al intent")
        intent = Intent(this, GP_AnadirTareas::class.java)
        startActivity(intent)
    }
}