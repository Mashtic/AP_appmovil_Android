package com.example.setion_ap.APP.Evaluaciones.Informes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.Main_Evaluaciones
import com.example.setion_ap.Adapters.AdapterInfo_Informes
import com.example.setion_ap.Procedures.ConnectSql
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectos
import com.example.setion_ap.R

class E_InformeGeneral : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var btnAtras: Button
    private lateinit var listView: ListView
    private var connectSql = ConnectSql()
    private lateinit var ArrayNombres: ArrayList<String>
    private lateinit var listaAdapters: ArrayAdapter<String>
    private lateinit var listaProyectos: List<vProyectos>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_einforme_general)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        cargarProyectos()
    }


    private fun initComponents(){
        btnAtras = findViewById<Button>(R.id.btnAtras_InformeGeneral)
        listView = findViewById<ListView>(R.id.listView_InformeGeneral)
        ArrayNombres= ArrayList<String>()


    }
    private fun initListeners(){
        btnAtras.setOnClickListener { fun_atras() }
        listView.setOnItemClickListener(this)
    }
    private fun cargarProyectos(){
        listaProyectos=GP_Procedures.get_Proyectos()
        for (proyecto in listaProyectos)
        {
            fun_anadirItem(proyecto.nombrePry.toString())
        }
    }
    private fun fun_anadirItem(nombre : String){
        ArrayNombres.add(nombre)
        listaAdapters= AdapterInfo_Informes(this,R.layout.__item_info_informes,ArrayNombres)
        listView.adapter=listaAdapters
    }
    private fun fun_atras(){
        intent = Intent(this, Main_Evaluaciones::class.java)
        startActivity(intent)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(this, E_InformeProyecto::class.java)
        intent.putExtra("proyectId",listaProyectos.get(position).id)
        startActivity(intent)
    }

}