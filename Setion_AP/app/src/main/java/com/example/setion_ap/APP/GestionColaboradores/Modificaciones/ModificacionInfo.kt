package com.example.setion_ap.APP.GestionColaboradores.Modificaciones

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
import com.example.setion_ap.Adapters.AdapterMC_Info
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vColaboradores
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class ModificacionInfo : AppCompatActivity() {
    private lateinit var btnAtras:Button

    private lateinit var listaColaboradores:ArrayList<vColaboradores>

    //LIST VIEW
    private lateinit var lvColaboradores : ListView

    private lateinit var arrayAdapter: ArrayAdapter<vColaboradores>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificacion_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        fun_CargarColaboradores()
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_MCINFO)
        lvColaboradores = findViewById(R.id.lvColaboradores_MCINFO)
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
        lvColaboradores.setOnItemClickListener { parent, view, position, id ->
            fun_ItemClicked_In_LV(parent, view, position, id)
        }
    }

    private fun fun_CargarColaboradores() {
        listaColaboradores = GP_Procedures.get_colaboradores()
        arrayAdapter = AdapterMC_Info(this, R.layout.__item_mi_tareas, listaColaboradores)
        lvColaboradores.adapter = arrayAdapter
    }

    private fun fun_ItemClicked_In_LV(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        GP_VariableGlobales.GC_Colaborador = listaColaboradores.get(position)
        startActivity(Intent(this,ModificacionColaborador::class.java))
    }
}