package com.example.setion_ap.APP.GestionColaboradores.Asignaciones

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Adapters.AdapterACAsignacion
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectos
import com.example.setion_ap.R

class AsignarProyecto : AppCompatActivity() {
    private lateinit var btnAtras: Button
    private lateinit var lvProyectos: ListView
    private lateinit var arrayAdapter: ArrayAdapter<vProyectos>
    private lateinit var listaProyectos: ArrayList<vProyectos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_asignar_proyecto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        cargarProyectos()
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_ACASIGNACION)
        lvProyectos = findViewById(R.id.lvProyectos_ACASIGNACION1)
    }

    private fun initListeners() {
        btnAtras.setOnClickListener { finish() }
        lvProyectos.setOnItemClickListener { parent, view, position, id ->
            fun_ItemClicked_In_AC(parent, view, position, id)
        }
    }

    private fun cargarProyectos() {
        listaProyectos = GP_Procedures.get_Proyectos()
        arrayAdapter = AdapterACAsignacion(this, R.layout.__item_ac_asignacion, listaProyectos)
        lvProyectos.adapter = arrayAdapter
    }

    // Definir la función fuera del alcance de initListeners
    private fun fun_ItemClicked_In_AC(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        println("Holaaaaaaaaa")
    }
}
