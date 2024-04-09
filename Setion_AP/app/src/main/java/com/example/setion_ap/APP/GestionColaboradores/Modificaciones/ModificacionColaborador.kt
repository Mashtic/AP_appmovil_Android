package com.example.setion_ap.APP.GestionColaboradores.Modificaciones

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class ModificacionColaborador : AppCompatActivity() {
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    //EDITTEXT
    private lateinit var edNombre: EditText
    private lateinit var edCorreo: EditText
    private lateinit var edTelefono: EditText
    private lateinit var edDepartamento: EditText
    private lateinit var edEstado: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificacion_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        cargarDatosEnEditText()
    }

    private fun cargarDatosEnEditText() {
        edNombre.setText(GP_VariableGlobales.GC_Colaborador?.nombreCompleto)
        edTelefono.setText(GP_VariableGlobales.GC_Colaborador?.telefono)
        edCorreo.setText(GP_VariableGlobales.GC_Colaborador?.correoElectronico)
        edDepartamento.setText("1")
        edEstado.setText("Trabajando")
    }

    private fun initComponents() {
        btnGuardar = findViewById(R.id.btnGuardar_MCCOLABORADOR)
        btnCancelar = findViewById(R.id.btnAtras_MCCOLABORADOR)

        edNombre = findViewById(R.id.edNombre_MCCOLABORADOR)
        edCorreo = findViewById(R.id.edCorreo_MCCOLABORADOR)
        edDepartamento = findViewById(R.id.edDepartamento_MCCOLABORADOR)
        edTelefono = findViewById(R.id.edTelefono_MCCOLABORADOR)
        edEstado = findViewById(R.id.edEstado_MCCOLABORADOR)
    }

    private fun initListeners() {
        btnGuardar.setOnClickListener{fun_guardar()}
        btnCancelar.setOnClickListener{finish()}
    }

    private fun fun_guardar() {
        if(edNombre.text.isNotEmpty() && edCorreo.text.isNotEmpty() &&
            edTelefono.text.isNotEmpty() && edDepartamento.text.isNotEmpty()){
            GP_Procedures.update_colaborador(GP_VariableGlobales.GC_Colaborador!!.cedula,
                edCorreo.text.toString(),
                edTelefono.text.toString(),
                edDepartamento.text.toString().toInt())

        } else {
            Toast.makeText(this, "Espacios vacios", Toast.LENGTH_SHORT).show()
        }
    }
}