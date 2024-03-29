package com.example.setion_ap.APP.Evaluaciones.Foros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class E_F_UsarForo_p1 : AppCompatActivity() {

    private lateinit var btnAceptar:Button
    private lateinit var btnAtras:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ef_usar_foro_p1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents(){
        btnAceptar = findViewById<Button>(R.id.btnAceptar_UF)
        btnAtras = findViewById<Button>(R.id.btnAtras_UF)
    }
    private fun initListeners(){
        btnAceptar.setOnClickListener { fun_aceptar() }
        btnAtras.setOnClickListener { fun_atras() }
    }
    private fun fun_aceptar(){
        //proceso con SQL MIEO
        //Notificar que se accedio a un
        intent = Intent(this, E_F_UsarForo_p2::class.java)
        startActivity(intent)
    }
    private fun fun_atras(){
        intent = Intent(this, E_F_CrearForo::class.java)
        startActivity(intent)
    }
}