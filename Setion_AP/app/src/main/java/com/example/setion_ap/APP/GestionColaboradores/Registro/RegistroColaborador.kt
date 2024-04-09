package com.example.setion_ap.APP.GestionColaboradores.Registro

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

class RegistroColaborador : AppCompatActivity() {
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    //EDITTEXT
    private lateinit var edNombre: EditText
    private lateinit var edCedula: EditText
    private lateinit var edCorreo: EditText
    private lateinit var edDepartamento: EditText
    private lateinit var edTelefono: EditText
    private lateinit var edProyecto: EditText
    private lateinit var edContrasenna: EditText
    private lateinit var edConContrasenna: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents() {
        btnGuardar = findViewById(R.id.btnGuardar_RC)
        btnCancelar = findViewById(R.id.btnCancelar_RC)

        edNombre = findViewById(R.id.edNombre_RC)
        edCedula = findViewById(R.id.edCedula_RC)
        edCorreo = findViewById(R.id.edCorreo_RC)
        edDepartamento = findViewById(R.id.edDepartamento_RC)
        edTelefono = findViewById(R.id.edTelefono_RC)
        edProyecto = findViewById(R.id.edProyectoId_RC)
        edContrasenna = findViewById(R.id.edContrasenna_RC)
        edConContrasenna = findViewById(R.id.edConContrasenna_RC)
    }

    private fun initListeners() {
        btnGuardar.setOnClickListener{fun_guardar()}
        btnCancelar.setOnClickListener{finish()}
    }

    private fun fun_guardar() {

        if(edNombre.text.isNotEmpty() && edCedula.text.isNotEmpty() &&
            edCorreo.text.isNotEmpty() && edDepartamento.text.isNotEmpty() &&
            edTelefono.text.isNotEmpty() && edProyecto.text.isNotEmpty()){
            if(validarCorreo(edCorreo.text.toString())){
                if(validarEnColaboradoresExistentes()){
                    if(edContrasenna.text.toString().equals(edConContrasenna.text.toString())) {
                        GP_Procedures.set_insertarColaborador(
                            edCedula.text.toString(),
                            edNombre.text.toString(),
                            edCorreo.text.toString(),
                            edDepartamento.text.toString().toInt(),
                            edTelefono.text.toString(),
                            "",
                            this
                        )
                        finish()
                    } else{
                        Toast.makeText(this, "Valide contraseña", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Colaborador ya existe", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Correo no valido", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Espacios vacíos", Toast.LENGTH_LONG).show()
        }
    }

    //Funcion para validadar si un colaborador ya esta registrado
    private fun validarEnColaboradoresExistentes(): Boolean {
        val colaboradores = GP_Procedures.get_colaboradores()
        for (e in colaboradores){
            if(e.cedula.equals(edCedula.text.toString()))
            {
                return false
            }
        }
        return true
    }


    fun validarCorreo(correo: String): Boolean {
        val colaboradores = GP_Procedures.get_colaboradores()
        for (e in colaboradores){
            if(e.correoElectronico.equals(edCorreo.text.toString())){
                return false
            }
        }
        val regex = Regex("^\\w+([.-]?\\w+)*@(estudiantec\\.cr|gmail\\.com)\$")
        return regex.matches(correo)
    }
}