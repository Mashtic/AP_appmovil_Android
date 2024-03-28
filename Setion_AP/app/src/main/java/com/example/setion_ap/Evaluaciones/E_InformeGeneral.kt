package com.example.setion_ap.Evaluaciones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Evaluaciones_Main
import com.example.setion_ap.R

class E_InformeGeneral : AppCompatActivity() {
    private lateinit var btnAtras: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_einforme_general)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }


    private fun initComponents(){
        btnAtras = findViewById<Button>(R.id.btnAtras_INFO)

    }
    private fun initListeners(){
        btnAtras.setOnClickListener { fun_atras() }

    }

    private fun fun_atras(){
        intent = Intent(this, Evaluaciones_Main::class.java)
        startActivity(intent)
    }

}