package com.example.setion_ap.APP.GestionProyectos.Modificacion_De_Informacion

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.R

class MI_EstadoDeTarea : AppCompatActivity() {
    private lateinit var btnCancelar: Button
    private lateinit var btnPorHacer: Button
    private lateinit var btnEnProgreso: Button
    private lateinit var btnFinalizado: Button

    private lateinit var listaTareas: ArrayList<vProyectoTareas>

    private lateinit var tarea:vProyectoTareas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mi_estado_de_tarea)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tareaID = intent.extras?.getInt("tareaID")
        listaTareas = GP_Procedures.get_ProyectoTareas()
        try {
            tarea = listaTareas.find { it.tareaId == tareaID }!!
        }catch (e: Exception){
            finish()
        }

        initComponents()
        initListeners()
        mostrarPantalla()
    }

    private fun mostrarPantalla() {
        val textView = findViewById<TextView>(R.id.tvInformacionMIESTADOTAREAS)
        textView.setText("Encargado: " + tarea.nombEnc + "\n" +
                "Descripción: " + tarea.descripcion
        )
    }

    private fun initComponents() {
        btnCancelar = findViewById(R.id.btnCancelarMIESTADOTAREAS)
        btnPorHacer = findViewById(R.id.btnPorHacerMIESTADOTAREAS)
        btnEnProgreso = findViewById(R.id.btnEnProgresoMIESTADOTAREAS)
        btnFinalizado = findViewById(R.id.btnFinalizadoMIESTADOTAREAS)
    }

    private fun initListeners() {
        btnCancelar.setOnClickListener{finish()}
        btnPorHacer.setOnClickListener{
            fun_actuaalizarEstado(1)
            finish()
        }
        btnEnProgreso.setOnClickListener{
            fun_actuaalizarEstado(2)
            finish()
        }
        btnFinalizado.setOnClickListener{
            fun_actuaalizarEstado(3)
            finish()
        }
    }

    private fun fun_actuaalizarEstado(idEstado: Int) {
        GP_Procedures.UPDATE_ESTADO_TAREA(idEstado, tarea.tareaId)
        Toast.makeText(this, "Estado cambiado", Toast.LENGTH_SHORT).show()
    }


}