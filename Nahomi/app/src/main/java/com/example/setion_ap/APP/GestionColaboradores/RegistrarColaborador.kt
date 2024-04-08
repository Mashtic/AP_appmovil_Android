package com.example.setion_ap.APP.GestionColaboradores

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R

class RegistrarColaborador : AppCompatActivity() {

    private lateinit var edNombre: EditText
    private lateinit var edCedula: EditText
    private lateinit var edCorreo: EditText
    private lateinit var edDepartamento: EditText
    private lateinit var edTelefono: EditText
    private lateinit var edEstado: EditText
    private lateinit var edNombreProyecto: EditText
    private lateinit var btnAceptar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_colaborador)

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        edNombre = findViewById(R.id.edNombre_RegistrarColaborador)
        edCedula = findViewById(R.id.edCedula_RegistrarColaborador)
        edCorreo = findViewById(R.id.edCorreo_RegistrarColaborador)
        edDepartamento = findViewById(R.id.edDepartamento_RegistrarColaborador)
        edTelefono = findViewById(R.id.edTelefono_RegistrarColaborador)
        edEstado = findViewById(R.id.edEstado_RegistrarColaborador)
        edNombreProyecto = findViewById(R.id.edNombreProyecto_RegistrarColaborador)
        btnAceptar = findViewById(R.id.btnAceptar_RegistrarColaborador)
        btnCancelar = findViewById(R.id.btnCancelar_RegistrarColaborador)
    }

    private fun initListeners() {
        btnAceptar.setOnClickListener {
            if (validarCampos()) {
                // Llamar al stored procedure para insertar el colaborador en la base de datos
                GP_Procedures.insertarColaborador(
                    edNombre.text.toString(),
                    edCedula.text.toString(),
                    edCorreo.text.toString(),
                    edDepartamento.text.toString(),
                    edTelefono.text.toString(),
                    edEstado.text.toString(),
                    edNombreProyecto.text.toString(),
                    this
                )
                // Mostrar mensaje de éxito
                Toast.makeText(this, "Colaborador registrado correctamente", Toast.LENGTH_SHORT).show()
                // Finalizar la actividad
                finish()
            } else {
                // Mostrar mensaje de error si hay campos vacíos
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancelar.setOnClickListener {
            // Finalizar la actividad
            finish()
        }
    }

    private fun validarCampos(): Boolean {
        return edNombre.text.isNotEmpty() &&
                edCedula.text.isNotEmpty() &&
                edCorreo.text.isNotEmpty() &&
                edDepartamento.text.isNotEmpty() &&
                edTelefono.text.isNotEmpty() &&
                edEstado.text.isNotEmpty() &&
                edNombreProyecto.text.isNotEmpty()
    }
}
