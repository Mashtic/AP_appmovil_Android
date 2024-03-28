package com.example.setion_ap.APP.Evaluaciones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R
import com.example.setion_ap.APP.Evaluaciones.BurndownChart.E_BC_Proyecto
import com.example.setion_ap.APP.Main_Evaluaciones

class E_BurndownCharts : AppCompatActivity() {

    private lateinit var btnProyecto1:Button
    private lateinit var btnproyecto2:Button
    private lateinit var btnProyecto3:Button
    private lateinit var btnAtras:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eburndown_charts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents(){
        btnProyecto1 = findViewById<Button>(R.id.btnProyecto1_BC)
        btnproyecto2= findViewById<Button>(R.id.btnProyecto2_BC)
        btnProyecto3 = findViewById<Button>(R.id.btnProyecto3_BC)
        btnAtras = findViewById<Button>(R.id.btnAtras_BC)

    }
    private fun initListeners(){
        btnProyecto1.setOnClickListener { fun_proyecto() }
        btnproyecto2.setOnClickListener { fun_proyecto() }
        btnProyecto3.setOnClickListener { fun_proyecto() }
        btnAtras.setOnClickListener { fun_atras() }

    }
    private fun fun_proyecto() {
        intent = Intent(this, E_BC_Proyecto::class.java)
        startActivity(intent)
    }
    private fun fun_atras() {
        intent = Intent(this, Main_Evaluaciones::class.java)
        startActivity(intent)
    }
}