package com.example.setion_ap.APP.GestionProyectos.Consulta_De_Proyectos

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.R

class CP_Tareas : AppCompatActivity() {
    private lateinit var btnAtras:Button

    //LISTA DE TAREAS
    private lateinit var listaTareas:List<vProyectoTareas>

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cp_tareas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val proyectoname = intent.extras?.getString("ProyectoName").orEmpty()//Recibe el nombre del proyecto
        val proyectId = intent.extras?.getInt("proyectId")//Recibe el idProyect para buscar las tareas de ese proyecto

        //Imprime las tareas en pantalla
        if(proyectId!=null){
            listaTareas = GP_Procedures.get_ProyectoTareas_fromAProyect(proyectId)
            fun_mostrarTareas(listaTareas)
        }

        //Pone el nombre del proyecto de titulo
        val titulo = findViewById<TextView>(R.id.tvTitulo_CPTAREAS)
        titulo.setText(titulo.text.toString() + " de " + proyectoname)

        initComponents()
        initListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun fun_mostrarTareas(listaTareas: List<vProyectoTareas>) {
        val tvPorhacer = findViewById<TextView>(R.id.tvPorhacer_CPTAREAS)
        val tvEnProgreso = findViewById<TextView>(R.id.tvEnProgreso_CPTAREAS)
        val tvFinalizado = findViewById<TextView>(R.id.tvFinalizado_CPTAREAS)
        for (e in listaTareas){
            if (e.nombEstadoTarea.equals("Por hacer"))
            {
                tvPorhacer.setText(tvPorhacer.text.toString() + "Encargado: " + e.nombEnc
                +"\nDescripción: " + e.descripcion + "\n\n")
            }else if(e.nombEstadoTarea.equals("En progreso")){
                tvEnProgreso.setText(tvPorhacer.text.toString() + "Encargado: " + e.nombEnc
                        +"\nDescripción: " + e.descripcion + "\n\n")
            }else{
                tvFinalizado.setText(tvPorhacer.text.toString() + "Encargado: " + e.nombEnc
                        +"\nDescripción: " + e.descripcion + "\n\n")
            }
        }
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_CPTAREAS)
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
    }
}