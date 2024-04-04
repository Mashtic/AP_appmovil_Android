package com.example.setion_ap.APP.GestionProyectos.Creacion_De_Proyectos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class GP_AnadirTareas : AppCompatActivity() {
    //EDITTEXT
    private lateinit var edNombre: EditText
    private lateinit var edDescripcion: EditText
    private lateinit var edStoryPoints: EditText

    //BOTONES
    private lateinit var btnAnadir:Button
    private lateinit var btnGuardar:Button
    private lateinit var btnCancelar:Button

    //TEXTVIEW
    private lateinit var tvTareasAñadidas:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gp_anadir_tareas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        //EDITTEXT
        edNombre = findViewById(R.id.edNombre_GPANADIRTAREAS)
        edDescripcion = findViewById(R.id.edDescripcion_GPANADIRTAREAS)
        edStoryPoints = findViewById(R.id.edStoryPoints_GPANADIRTAREAS)

        //BUTTONS
        btnAnadir = findViewById(R.id.btnAnadir_GPANADIRTAREAS)
        btnCancelar = findViewById(R.id.btnCancelar_GPANADIRTAREAS)
        btnGuardar = findViewById(R.id.btnGuardar_GPANADIRTAREAS)

        //TEXTVIEW
        tvTareasAñadidas = findViewById(R.id.tvTareasAñadidas_GPANADIRTAREAS)
    }

    private fun initListeners() {
        btnAnadir.setOnClickListener{fun_anadir()}
        btnGuardar.setOnClickListener{fun_Guardar()}
        btnCancelar.setOnClickListener{fun_Cancelar()}
    }

    private fun fun_anadir() {
        if (edNombre.text.isNotEmpty() && edDescripcion.text.isNotEmpty() && edStoryPoints.text.isNotEmpty()){
            val tarea = vProyectoTareas(
                idProyecto = 1,
                nombrePry = "Proyecto",
                tareaId = 1,
                descripcion = edDescripcion.text.toString(),
                storyPoint = edStoryPoints.text.toString().toInt(),
                nombEstadoTarea = "En proceso",
                nombEnc = edNombre.text.toString()
            )
            GP_VariableGlobales.listaTareasAnadidas.add(tarea)

            fun_anadirEnListaTareas()
            edNombre.setText("")
            edDescripcion.setText("")
            edStoryPoints.setText("")
            Toast.makeText(this, "Tarea añadida", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "No se añadió", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fun_anadirEnListaTareas() {
        tvTareasAñadidas.setText("")
        for(e in GP_VariableGlobales.listaTareasAnadidas){
            tvTareasAñadidas.setText(tvTareasAñadidas.text.toString() + e.descripcion + "\n")
        }
    }

    private fun fun_tvFuncionesAnadidas() {

    }

    private fun fun_Guardar() {
        finish()
    }

    private fun fun_Cancelar() {
        finish()
    }
}