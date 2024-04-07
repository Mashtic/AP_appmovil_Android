package com.example.setion_ap.APP.Evaluaciones.BurndownChart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R
import com.example.setion_ap.APP.Main_Evaluaciones
import com.example.setion_ap.Adapters.AdapterInfo_BurndownCharts
import com.example.setion_ap.Procedures.ConnectSql
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectos

class E_BurndownCharts : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var btnAtras:Button
    private lateinit var ArrayLista: ArrayList<String>
    private lateinit var mAdapter: ArrayAdapter<String>

    private lateinit var listView: ListView
    private var connectSql = ConnectSql()
    private lateinit var listaProyectos:List<vProyectos>

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
        fun_anadirProyectos()
    }
    private fun initComponents(){
        btnAtras = findViewById<Button>(R.id.btnAtras_BC)
        ArrayLista = ArrayList<String>()
        listView= findViewById<ListView>(R.id.listView_BC)


    }
    private fun initListeners(){
        btnAtras.setOnClickListener { fun_atras() }
        listView.setOnItemClickListener(this)

    }

    private fun fun_anadirProyectos() {
        listaProyectos = GP_Procedures.get_Proyectos()
        for (proyecto in listaProyectos)
        {
            fun_anadirItem(proyecto.nombrePry.toString())
        }
    }

    private fun fun_anadirItem(nombre:String){
        ArrayLista.add(nombre)
        mAdapter= AdapterInfo_BurndownCharts(this,R.layout.__item_info_burndowncharts,ArrayLista)
        listView.adapter=mAdapter
    }
    private fun fun_atras() {
        intent = Intent(this, Main_Evaluaciones::class.java)
        startActivity(intent)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(this, E_BC_Proyecto::class.java)
        intent.putExtra("proyectId",listaProyectos.get(position).id)
        startActivity(intent)
    }
}