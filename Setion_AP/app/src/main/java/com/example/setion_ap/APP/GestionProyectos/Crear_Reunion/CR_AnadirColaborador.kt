package com.example.setion_ap.APP.GestionProyectos.Crear_Reunion

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Adapters.AdapterGP_AnadirColaborador
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vColaboradores
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class CR_AnadirColaborador : AppCompatActivity(), AdapterView.OnItemClickListener {
    //BUTTONS
    private lateinit var btnCancelar: Button
    private lateinit var btnGuardar: Button
    private lateinit var imgLupa: ImageView

    //LISTA COLABORADORES
    private lateinit var listColaradoresLibres:ArrayList<vColaboradores>

    //LIST VIEW
    private lateinit var listView: ListView
    private lateinit var arrayAdapter: ArrayAdapter<vColaboradores>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cr_anadir_colaborador)
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
        btnCancelar = findViewById(R.id.btnCancelar_CRANADIRCOLABORADOR)
        btnGuardar = findViewById(R.id.btnGuardar_CRANADIRCOLABORADOR)
        imgLupa = findViewById(R.id.imgLUPA_CRANADIRCOLABORADOR)
        listView = findViewById(R.id.listView_CRANADIRCOLABORADOR)
    }

    private fun initListeners() {
        btnGuardar.setOnClickListener{finish()}
        btnCancelar.setOnClickListener{
            GP_VariableGlobales.listaColaboradoresAnadidos.clear()
            finish()
        }
        listView.setOnItemClickListener(this)
        imgLupa.setOnClickListener{}
    }

    //FUNCIONES
    private fun fun_cargarColaboradores() {
        listColaradoresLibres = GP_Procedures.get_colaboradores()
        arrayAdapter = AdapterGP_AnadirColaborador(this, R.layout.__item_gp_anadir_colaborador, listColaradoresLibres)
        listView.adapter = arrayAdapter
    }

    private fun fun_selecionarItem(view: View?, position: Int) {
        if (view != null) {

            val cardView = view.findViewById<CardView>(R.id.i__cardView__GPANADIRCOLABORADOR)
            val textViewPlus = view.findViewById<TextView>(R.id.i__textViewPlus__GPANADIRCOLABORADOR)
            if (cardView.backgroundTintList == ColorStateList.valueOf(Color.parseColor("#002854"))) {  //Color Azul
                cardView.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ADE46D")) //Color verde
                textViewPlus.setText("-")

                //Se añade el colaborador a los seleccionados
                val item = arrayAdapter.getItem(position)!!
                GP_VariableGlobales.listaColaboradoresAnadidos.add(item)
            }else{
                cardView.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#002854")) // Color Azul
                textViewPlus.setText("+")

                //Eliminamos el elemento
                val item = arrayAdapter.getItem(position)!!
                for (e in GP_VariableGlobales.listaColaboradoresAnadidos){
                    if (item.cedula.equals(e.cedula)){
                        GP_VariableGlobales.listaColaboradoresAnadidos.remove(e)
                        break
                    }
                }
            }
        }
    }

    //LISTENER
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
        fun_selecionarItem(view, position)
    }
}