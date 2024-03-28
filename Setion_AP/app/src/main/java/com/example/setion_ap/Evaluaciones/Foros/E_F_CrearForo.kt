package com.example.setion_ap.Evaluaciones.Foros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Evaluaciones.E_CreacionDeForos
import com.example.setion_ap.R

class E_F_CrearForo : AppCompatActivity() {

    private lateinit var btnAceptar:Button
    private lateinit var btnCancelar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ef_crear_foro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents(){
        btnAceptar = findViewById<Button>(R.id.btnAceptar_CF)
        btnCancelar = findViewById<Button>(R.id.btnCancelar_CF)
    }
    private fun initListeners(){
        btnAceptar.setOnClickListener { fun_aceptar() }
        btnCancelar.setOnClickListener { fun_cancelar() }
    }
    private fun fun_aceptar(){
        //proceso con base de datos mieo
        //añadir la notificación de cambio
    }
    private fun fun_cancelar(){
        intent = Intent(this, E_CreacionDeForos::class.java)
        startActivity(intent)
    }
}