package com.example.setion_ap.APP.GestionColaboradores.Asignaciones

import android.annotation.SuppressLint
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
import com.example.setion_ap.Adapters.AdapterACAnadirColaborador
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vColaboradoresCompleto
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class AgregarColaborador : AppCompatActivity(){
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    //LISTA COLABORADORES
    private lateinit var listColaradoresLibres: ArrayList<vColaboradoresCompleto>

    //LIST VIEW
    private lateinit var listView: ListView
    private lateinit var arrayAdapter: ArrayAdapter<vColaboradoresCompleto>

    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gp_anadir_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        GP_VariableGlobales.listaColaboradoresAnadidos.clear()

        initComponents()
        initListeners()
        fun_cargarColaboradores()
    }

    //INITS
    private fun initComponents() {
        btnGuardar = findViewById(R.id.btnGuardar_ACANADIRCOLABORADOR)
        btnCancelar = findViewById(R.id.btnCancelar_ACANADIRCOLABORADOR)
        listView = findViewById(R.id.lvColaboradores_ACANADIRCOLABORADOR)
    }

    private fun initListeners() {
        btnGuardar.setOnClickListener {guardar()}
        btnCancelar.setOnClickListener {
            GP_VariableGlobales.AC_ArrayColaboradores.clear()
            finish()
        }
    }

    private fun guardar() {
        val colaboradores = GP_VariableGlobales.AC_ArrayColaboradores
        for (e in colaboradores){
            GP_Procedures.update_reasignarColabadorAProyecto(e.cedula, GP_VariableGlobales.AC_Proyecto!!.id)
        }
        Toast.makeText(this, "Colaboradores añadidos", Toast.LENGTH_LONG).show()
        finish()
    }

    //FUNCIONES
    private fun fun_cargarColaboradores() {
        listColaradoresLibres = GP_VariableGlobales.get_colaboradores_libres_completos()
        println(listColaradoresLibres.size)
        arrayAdapter = AdapterACAnadirColaborador(this, R.layout.__item_ac_anadir_colaborador, listColaradoresLibres)
        listView.adapter = arrayAdapter
    }
}
