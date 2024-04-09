package com.example.setion_ap.APP.GestionColaboradores.Registro

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R
import android.widget.EditText

class RegistroColaborador : AppCompatActivity() {

    private lateinit var edNombre: EditText
    private lateinit var edCedula: EditText
    private lateinit var edCorreo: EditText
    private lateinit var edDepartamento: EditText
    private lateinit var edTelefono: EditText
    private lateinit var edEstado: EditText
    private lateinit var edNombreProyecto: EditText  // Campo para el nombre del proyecto en caso de Estado Trabajando

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
    private fun initComponents() {
        edNombre = findViewById(R.id.edNombre)
        edCedula = findViewById(R.id.edCedula)
        edCorreo = findViewById(R.id.edCorreo)
        edDepartamento = findViewById(R.id.edDepartamento)
        edTelefono = findViewById(R.id.edTelefono)
        edEstado = findViewById(R.id.edEstado)
        edNombreProyecto = findViewById(R.id.edNombreProyecto)
    }

    // Función para guardar los datos del colaborador
    private fun guardarColaborador() {
        val nombre = edNombre.text.toString()
        val cedula = edCedula.text.toString()
        val correo = edCorreo.text.toString()
        val departamento = edDepartamento.text.toString()
        val telefono = edTelefono.text.toString()
        val estado = edEstado.text.toString()
        val nombreProyecto = if (estado == "Trabajando") edNombreProyecto.text.toString() else ""

        // Guarda los datos del colaborador

    }

}