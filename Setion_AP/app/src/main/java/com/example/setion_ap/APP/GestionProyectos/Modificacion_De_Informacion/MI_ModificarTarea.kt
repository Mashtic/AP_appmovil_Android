package com.example.setion_ap.APP.GestionProyectos.Modificacion_De_Informacion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.GestionProyectos.Consulta_De_Proyectos.CP_Tareas
import com.example.setion_ap.Adapters.AdapterGP_ConsultaProyectos
import com.example.setion_ap.Adapters.AdapterMI_ModificarTarea
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectos
import com.example.setion_ap.R

class MI_ModificarTarea : AppCompatActivity(), AdapterView.OnItemClickListener  {
    //BUTTONS
    private lateinit var btnAtras: Button
    private lateinit var imgLupa: ImageView

    //ARRAY ADAPTER
    private lateinit var mAdapter: ArrayAdapter<vProyectos>

    //List
    private lateinit var listaProyectos: List<vProyectos>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mi_modificar_tarea)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_MIMODIFICARTAREAS)
        imgLupa = findViewById(R.id.imgLUPA_MIMODIFICARTAREAS)
        listView = findViewById(R.id.listView_MIMODIFICARTAREAS)
        listaProyectos = GP_Procedures.get_Proyectos()
        fun_MostrarListView()
    }

    private fun fun_MostrarListView() {
        for (e in listaProyectos) {
            mAdapter = AdapterMI_ModificarTarea(this, R.layout.__item_mi_modificar_tarea, listaProyectos)
            listView.adapter = mAdapter
        }
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
        imgLupa.setOnClickListener{}
        listView.setOnItemClickListener(this)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(this, MI_Tareas::class.java)
        intent.putExtra("ProyectoName", listaProyectos.get(position).nombrePry.toString())
        intent.putExtra("proyectId", listaProyectos.get(position).id)
        startActivity(intent)
    }
}