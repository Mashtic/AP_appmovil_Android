package com.example.setion_ap.APP.InicioRegistro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R

class Registro : AppCompatActivity() {

    private lateinit var edNombre: EditText
    private lateinit var edApellidos: EditText
    private lateinit var edCorreo: EditText
    private lateinit var edContrasenna: EditText
    private lateinit var edConfirmarContrasenna: EditText
    private lateinit var btnAceptar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        initComponents()
        initListeners()
    }

    // Inicializa los componentes de la interfaz de usuario
    private fun initComponents() {
        edNombre = findViewById(R.id.edNombre_Registro)
        edApellidos = findViewById(R.id.edApellidos_Registro)
        edCorreo = findViewById(R.id.edCorreo_Registro)
        edContrasenna = findViewById(R.id.edContrasenna_Registro)
        edConfirmarContrasenna = findViewById(R.id.edConfirmarContrasenna_Registro)
        btnAceptar = findViewById(R.id.btnAceptar_Registro)
        btnCancelar = findViewById(R.id.btnCancelar_Registro)
    }

    // Inicializa los listeners de los botones
    private fun initListeners() {
        btnAceptar.setOnClickListener {
            // Validar que todos los campos estén completos
            if (validarCampos()) {
                // Verificar que las contraseñas coincidan
                if (edContrasenna.text.toString() == edConfirmarContrasenna.text.toString()) {
                    // Insertar el usuario en la base de datos
                    GP_Procedures.insertarUsuario(
                        edNombre.text.toString(),
                        edApellidos.text.toString(),
                        edCorreo.text.toString(),
                        edContrasenna.text.toString(),
                        this
                    )
                    // Mostrar mensaje de éxito
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    // Finalizar la actividad
                    finish()
                } else {
                    // Mostrar mensaje de error si las contraseñas no coinciden
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Mostrar mensaje de error si hay campos vacíos
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Listener para el botón Cancelar
        btnCancelar.setOnClickListener {
            // Finalizar la actividad
            finish()
        }
    }

    // Función para validar que todos los campos estén completos
    private fun validarCampos(): Boolean {
        return edNombre.text.isNotEmpty() &&
                edApellidos.text.isNotEmpty() &&
                edCorreo.text.isNotEmpty() &&
                edContrasenna.text.isNotEmpty() &&
                edConfirmarContrasenna.text.isNotEmpty()
    }
}
