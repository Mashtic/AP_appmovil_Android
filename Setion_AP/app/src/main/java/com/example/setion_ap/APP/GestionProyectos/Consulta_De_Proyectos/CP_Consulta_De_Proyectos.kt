package com.example.setion_ap.APP.GestionProyectos.Consulta_De_Proyectos

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
import com.example.setion_ap.R
class CP_Consulta_De_Proyectos : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var btnAtras:Button
    private lateinit var imgLupa:ImageView

    private lateinit var ArrayLista: ArrayList<String>
    private lateinit var mAdapter: ArrayAdapter<String>

    private lateinit var listView: ListView


    //CONEXION SQL
    private var connectSql = ConnectSql()

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
        var array = GP_Procedures.GET_PROYECTOS(connectSql)
        for (e in array){
            fun_AnadirItem(e.toString())
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

    private fun fun_AnadirItem(texto: String) {
        ArrayLista.add(texto)
        mAdapter = AdapterGP_ConsultaProyectos(this, R.layout.__item_cp_consulta_proyectos, ArrayLista)
        listView.adapter = mAdapter
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "Elemento seleccionado: " + position, Toast.LENGTH_SHORT).show()
    }
}