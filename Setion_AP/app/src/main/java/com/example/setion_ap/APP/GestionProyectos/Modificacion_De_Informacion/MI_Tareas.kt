package com.example.setion_ap.APP.GestionProyectos.Modificacion_De_Informacion

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Adapters.AdapterGP_ConsultaProyectos
import com.example.setion_ap.Adapters.AdapterMI_Tareas
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.R

class MI_Tareas : AppCompatActivity() {

    private lateinit var btnAtras: Button

    //LISTA DE TAREAS
    private lateinit var listaTareas:ArrayList<vProyectoTareas>

    //LIST VIEW
    private lateinit var listView_PorHacer : ListView
    private lateinit var listView_EnProgreso : ListView
    private lateinit var listView_Finalizado : ListView

    //ADAPTERS
    private lateinit var ArrayAdapter_PorHacer: ArrayAdapter<vProyectoTareas>
    private lateinit var ArrayAdapter_EnProgreso: ArrayAdapter<vProyectoTareas>
    private lateinit var ArrayAdapter_Finalizado: ArrayAdapter<vProyectoTareas>

    //INT
    private var proyectId: Int = 1

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mi_tareas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()

        val proyectoname = intent.extras?.getString("ProyectoName").orEmpty()//Recibe el nombre del proyecto
        val proyectIdx = intent.extras?.getInt("proyectId")//Recibe el idProyect para buscar las tareas de ese proyecto

        //Imprime las tareas en pantalla
        if(proyectIdx!=null){
            proyectId = proyectIdx
            fun_CargarTodasTareas()
        }

        //Pone el nombre del proyecto de titulo
        val titulo = findViewById<TextView>(R.id.tvTitulo_MITAREAS)
        titulo.setText(titulo.text.toString() + " de " + proyectoname)
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_MITAREAS)
        listView_PorHacer = findViewById(R.id.listView_PorHacer_MITAREAS)
        listView_EnProgreso = findViewById(R.id.listView_EnProgreso_MITAREAS)
        listView_Finalizado = findViewById(R.id.listView_Finalizado_MITAREAS)

    }

    private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
        listView_PorHacer.setOnItemClickListener { parent, view, position, id ->
            fun_ItemClicked_In_PorHacer(parent, view, position, id)
        }

        listView_EnProgreso.setOnItemClickListener { parent, view, position, id ->
            fun_ItemClicked_In_EnProgreso(parent, view, position, id)
        }

        listView_Finalizado.setOnItemClickListener { parent, view, position, id ->
            fun_ItemClicked_In_Finalizado(parent, view, position, id)
        }
    }

    private fun fun_CargarTodasTareas() {
        listaTareas = GP_Procedures.get_ProyectoTareas_fromAProyect(proyectId)
        var array1 = ArrayList<vProyectoTareas>()
        var array2 = ArrayList<vProyectoTareas>()
        var array3 = ArrayList<vProyectoTareas>()
        for (e in listaTareas){
            if (e.nombEstadoTarea.equals("Por hacer")){
                array1.add(e)
            }else if (e.nombEstadoTarea.equals("En progreso")){
                array2.add(e)
            }else{
                array3.add(e)
            }
        }
        mostrarListView_PorHacer(array1)
        mostrarListView_EnProgreso(array2)
        mostrarListView_Finalizado(array3)
    }

    private fun mostrarListView_PorHacer(arraylist : ArrayList<vProyectoTareas>) {
        ArrayAdapter_PorHacer = AdapterMI_Tareas(this, R.layout.__item_mi_tareas, arraylist)
        listView_PorHacer.adapter = ArrayAdapter_PorHacer
    }

    private fun mostrarListView_EnProgreso(arraylist : ArrayList<vProyectoTareas>) {
        ArrayAdapter_EnProgreso = AdapterMI_Tareas(this, R.layout.__item_mi_tareas, arraylist)
        listView_EnProgreso.adapter = ArrayAdapter_EnProgreso
    }

    private fun mostrarListView_Finalizado(arraylist : ArrayList<vProyectoTareas>) {
        ArrayAdapter_Finalizado = AdapterMI_Tareas(this, R.layout.__item_mi_tareas, arraylist)
        listView_Finalizado.adapter = ArrayAdapter_Finalizado
    }

    private fun fun_ItemClicked_In_PorHacer(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(this, MI_EstadoDeTarea::class.java)
        val tareaID:Int = ArrayAdapter_PorHacer.getItem(position)!!.tareaId
        intent.putExtra("tareaID",tareaID)
        startForResult.launch(intent)
    }

    private fun fun_ItemClicked_In_EnProgreso(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(this, MI_EstadoDeTarea::class.java)
        val tareaID:Int = ArrayAdapter_EnProgreso.getItem(position)!!.tareaId
        intent.putExtra("tareaID",tareaID)
        startForResult.launch(intent)
    }

    private fun fun_ItemClicked_In_Finalizado(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent(this, MI_EstadoDeTarea::class.java)
        val tareaID:Int = ArrayAdapter_Finalizado.getItem(position)!!.tareaId
        intent.putExtra("tareaID",tareaID)
        startForResult.launch(intent)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
        fun_CargarTodasTareas()
        ArrayAdapter_PorHacer.notifyDataSetChanged()
        ArrayAdapter_EnProgreso.notifyDataSetChanged()
        ArrayAdapter_Finalizado.notifyDataSetChanged()
    }
}
