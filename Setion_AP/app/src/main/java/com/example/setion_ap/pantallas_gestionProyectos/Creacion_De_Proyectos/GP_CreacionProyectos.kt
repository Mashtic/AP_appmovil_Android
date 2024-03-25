package com.example.setion_ap.pantallas_gestionProyectos.Creacion_De_Proyectos

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gp_creacion_proyectos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgLupa = findViewById<ImageView>(R.id.imgLUPA)
        imgLupa.setOnClickListener{fun_AnadirColaborador()}

        val btnHistorialDeCambios = findViewById<Button>(R.id.btnHistorialDeCambios)
        

        val btnCancelar = findViewById<Button>(R.id.btnCancelar)
        btnCancelar.setOnClickListener { (finish()) }

        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        btnGuardar.setOnClickListener { fun_GuardarDatos() }
    }

    private fun fun_AnadirColaborador() {
        intent = Intent(this, GP_AnadirColaborador::class.java)
        startActivity(intent)
    }

    private fun fun_GuardarDatos() {
        /*Codigo para guardar los datos*/
        finish()
    }
}