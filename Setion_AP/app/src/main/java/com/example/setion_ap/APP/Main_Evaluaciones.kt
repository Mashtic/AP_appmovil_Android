package com.example.setion_ap.APP

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.Evaluaciones.E_BurndownCharts
import com.example.setion_ap.APP.Evaluaciones.E_InformeGeneral
import com.example.setion_ap.APP.Evaluaciones.E_CreacionDeForos
import com.example.setion_ap.R

class Main_Evaluaciones : AppCompatActivity() {

    private lateinit var btnInformeGeneral:Button
    private lateinit var btnBurndownChart:Button
    private lateinit var btnCreacionDeForos:Button
    private lateinit var btnAtras:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_evaluaciones_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents(){
        btnInformeGeneral = findViewById<Button>(R.id.btnInformeGeneral_MAINEVAL)
        btnBurndownChart= findViewById<Button>(R.id.btnBurndownCharts_MAINEVAL)
        btnCreacionDeForos = findViewById<Button>(R.id.btnCreacionDeForos_MAINEVAL)
        btnAtras = findViewById<Button>(R.id.btnAtras_MAINEVAL)

    }
    private fun initListeners(){
        btnInformeGeneral.setOnClickListener { fun_informeGeneral() }
        btnBurndownChart.setOnClickListener { fun_burndownCharts() }
        btnCreacionDeForos.setOnClickListener { fun_creacionForos() }
        btnAtras.setOnClickListener { fun_atras() }

    }
    private fun fun_informeGeneral() {
        intent = Intent(this, E_InformeGeneral::class.java)
        startActivity(intent)
    }
    private fun fun_burndownCharts() {
        intent = Intent(this, E_BurndownCharts::class.java)
        startActivity(intent)
    }
    private fun fun_creacionForos() {
        intent = Intent(this, E_CreacionDeForos::class.java)
        startActivity(intent)
    }
    private fun fun_atras() {
        println("Hola")
    }
}