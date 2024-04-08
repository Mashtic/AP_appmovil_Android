package com.example.setion_ap.APP.Evaluaciones.BurndownChart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.Main_Evaluaciones
import com.example.setion_app.R

class E_BC_Proyecto : AppCompatActivity() {
    private lateinit var btnAtras:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ebc_proyecto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }


    private fun initComponents(){
        btnAtras = findViewById<Button>(R.id.btnAtras_P)

    }
    private fun initListeners(){
        btnAtras.setOnClickListener { fun_atras() }

    }

    private fun fun_atras(){
        intent = Intent(this, Main_Evaluaciones::class.java)
        startActivity(intent)
    }
}