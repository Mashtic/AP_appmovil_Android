package com.example.setion_ap.APP.GestionColaboradores.Asignaciones

import android.database.SQLException
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Adapters.AdapterACEliminarColaborador
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vColaboradoresCompleto
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class EliminarColaborador : AppCompatActivity() {
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    //LISTA COLABORADORES
    private lateinit var listColaradoresLibres: ArrayList<vColaboradoresCompleto>

    //LIST VIEW
    private lateinit var listView: ListView
    private lateinit var arrayAdapter: ArrayAdapter<vColaboradoresCompleto>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar_colaborador)
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
        btnGuardar = findViewById(R.id.btnEliminar_ACELIMINARCOLABARODOR)
        btnCancelar = findViewById(R.id.btnCancelar_ACELIMINARCOLABARODOR)
        listView = findViewById(R.id.lvColaboradores_ACELIMINARCOLABARODOR)
    }

    private fun initListeners() {
        btnGuardar.setOnClickListener {eliminar()}
        btnCancelar.setOnClickListener {
            GP_VariableGlobales.AC_ArrayColaboradores.clear()
            finish()
        }
    }

    private fun eliminar() {
        val colaboradores = GP_VariableGlobales.AC_ArrayColaboradores
        for (e in colaboradores){
            GP_Procedures.update_eliminarColabadorDeProyecto(e.cedula)
        }
        Toast.makeText(this, "Colaboradores añadidos", Toast.LENGTH_LONG).show()
        finish()
    }

    //FUNCIONES
    private fun fun_cargarColaboradores() {
        listColaradoresLibres = GP_VariableGlobales.get_colaboradoresDeProyecto()
        arrayAdapter = AdapterACEliminarColaborador(this, R.layout.__item_ac_anadir_colaborador, listColaradoresLibres)
        listView.adapter = arrayAdapter
    }
}
