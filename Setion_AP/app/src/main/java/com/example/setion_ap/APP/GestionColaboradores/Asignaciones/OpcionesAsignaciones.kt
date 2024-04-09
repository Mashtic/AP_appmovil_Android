package com.example.setion_ap.APP.GestionColaboradores.Asignaciones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class OpcionesAsignaciones : AppCompatActivity() {
    private lateinit var btnGAI:Button
    private lateinit var btnAnadirColaborador:Button
    private lateinit var btnEliminarColaborador:Button
    private lateinit var btnAtras:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_opciones_asignaciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnGAI = findViewById(R.id.btnGestionarActividades_ACOPCIONESASIGNACION)
        btnAnadirColaborador = findViewById(R.id.btnAnadirColaborado_ACOPCIONESASIGNACION)
        btnEliminarColaborador = findViewById(R.id.btnEliminarColaborador_ACOPCIONESASIGNACION)
        btnAtras = findViewById(R.id.btnAtras_ACOPCIONESASIGNACION)
    }

    private fun initListeners() {
        btnGAI.setOnClickListener{ startActivity(Intent(this, GestionAsignaciones::class.java)) }
        btnAnadirColaborador.setOnClickListener{ startActivity(Intent(this, AgregarColaborador::class.java)) }
        btnEliminarColaborador.setOnClickListener{ startActivity(Intent(this, EliminarColaborador::class.java)) }
        btnAtras.setOnClickListener{ finish() }
    }
}