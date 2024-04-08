package com.example.setion_ap.APP.InicioRegistro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import com.example.setion_app.R

class InicioRegistro : AppCompatActivity() {
    // Variables para almacenar
    private lateinit var edUsuarioCorreo: EditText
    private lateinit var edContrasenna: EditText
    private lateinit var btnIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_registro)

        // Inicializa elementos de la interfaz de usuario
        edUsuarioCorreo = findViewById(R.id.edUsuarioCorreo)
        edContrasenna = findViewById(R.id.edContrasenna)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        // Configura el listener para el botón de iniciar sesión
        btnIniciarSesion.setOnClickListener {
            // Obtiene los valores de los campos de usuario/correo y contraseña
            val usuarioCorreo = edUsuarioCorreo.text.toString()
            val contrasenna = edContrasenna.text.toString()

            // Verifica las credenciales utilizando el procedimiento almacenado
            val credencialesCorrectas = GP_Procedures.verificarCredenciales(usuarioCorreo, contrasenna)

            // Muestra un mensaje dependiendo del resultado de la verificación
            if (credencialesCorrectas) {
                // Inicia la actividad principal si las credenciales son correctas
                val intent = Intent(this, ActividadPrincipal::class.java)
                startActivity(intent)
                finish() // Finalizar esta actividad para evitar que el usuario vuelva atrás
            } else {
                // Muestra un mensaje de error si las credenciales son incorrectas
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
