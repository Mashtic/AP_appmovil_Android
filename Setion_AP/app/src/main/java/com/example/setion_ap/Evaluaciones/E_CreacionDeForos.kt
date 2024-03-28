package com.example.setion_ap.Evaluaciones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Evaluaciones.Foros.E_F_CrearForo
import com.example.setion_ap.Evaluaciones.Foros.E_F_UsarForo_p1
import com.example.setion_ap.Evaluaciones_Main
import com.example.setion_ap.R

class E_CreacionDeForos : AppCompatActivity() {
    private lateinit var btnUsarForo:Button
    private lateinit var btnCrearForo:Button
    private lateinit var btnAtras:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ecreacion_de_foros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents(){
        btnUsarForo = findViewById<Button>(R.id.btnUsarForo_F)
        btnCrearForo = findViewById<Button>(R.id.btnCrearForo_F)
        btnAtras = findViewById<Button>(R.id.btnAtras_F)
    }

    private fun initListeners(){
        btnUsarForo.setOnClickListener { fun_usarForo() }
        btnCrearForo.setOnClickListener { fun_crearForo() }
        btnAtras.setOnClickListener { fun_atras() }
    }
    private fun fun_usarForo (){
        intent= Intent(this, E_F_UsarForo_p1::class.java)
        startActivity(intent)
    }
    private fun fun_crearForo(){
        intent= Intent(this, E_F_CrearForo::class.java)
        startActivity(intent)
    }
    private fun fun_atras(){
        intent= Intent(this, Evaluaciones_Main::class.java)
        startActivity(intent)

    }
}