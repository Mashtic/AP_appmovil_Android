package com.example.setion_ap.APP.InicioRegistro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class IN_PaginaPrincipal : AppCompatActivity() {
    private lateinit var btnInicioSesion:Button
    private lateinit var btnRegistrarse:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_in_pagina_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnInicioSesion = findViewById(R.id.btnIniciarSesion_IN)
        btnRegistrarse = findViewById(R.id.btnRegistrarse_IN)
    }

    private fun initListeners() {
        btnInicioSesion.setOnClickListener{fun_iniciarSesion()}
        btnRegistrarse.setOnClickListener{fun_Registrarse()}
    }

    private fun fun_iniciarSesion() {
        val intent = Intent(this, IN_InicioSesion::class.java)
        startActivity(intent)
    }

    private fun fun_Registrarse() {
        val intent = Intent(this, IN_Registrarse::class.java)
        startActivity(intent)
    }
}