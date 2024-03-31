package com.example.setion_ap.APP.GestionProyectos.Consulta_De_Proyectos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Adapters.AdapterGP_ConsultaProyectos
import com.example.setion_ap.Procedures.ConnectSql
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectos
import com.example.setion_ap.R
class CP_Consulta_De_Proyectos : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var btnAtras:Button
    private lateinit var imgLupa:ImageView

    private lateinit var ArrayLista: ArrayList<String>
    private lateinit var mAdapter: ArrayAdapter<String>

    private lateinit var listView: ListView

    //CONEXION SQL
    private var connectSql = ConnectSql()
    private lateinit var listaProyectos:List<vProyectos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cp_consulta_de_proyectos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        cargarTodosProyectos()
    }

    private fun cargarTodosProyectos() {
        println("Procedemos a cargar")
        listaProyectos = GP_Procedures.get_Proyectos()
        for (e in listaProyectos){
            fun_AnadirItem(e.nombrePry.toString())
        }
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_CPCONSULTAPROYECTOS)
        imgLupa = findViewById(R.id.imgLUPA_CPCONSULTAPROYECTOS)

        listView = findViewById(R.id.listView_CPCONSULTAPROYECTOS)
        ArrayLista = ArrayList<String>()
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
        imgLupa.setOnClickListener{}
        listView.setOnItemClickListener(this)
    }

    //Añade la LISTA DE PROYECTOS
    private fun fun_AnadirItem(texto: String) {
        ArrayLista.add(texto)
        mAdapter = AdapterGP_ConsultaProyectos(this, R.layout.__item_cp_consulta_proyectos, ArrayLista)
        listView.adapter = mAdapter
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //Toast.makeText(this, "Elemento seleccionado: " + position, Toast.LENGTH_SHORT).show()

        //Pasa a la pantalla de tareas para mostrar las tareas del proyecto que se seleccionó
        val intent = Intent(this, CP_Tareas::class.java)
        intent.putExtra("ProyectoName", listaProyectos.get(position).nombrePry.toString())
        intent.putExtra("proyectId", listaProyectos.get(position).id)
        startActivity(intent)

        println("Lo que se seleciono: "+ mAdapter.getItem(position) + "\n" + "Posicion: " + position)
    }
}