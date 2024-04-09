package com.example.setion_ap.APP.InicioRegistro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.PaginaPrincipal
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class IN_InicioSesion : AppCompatActivity() {
    private lateinit var btnIngresar: Button
    private lateinit var btnCancelar: Button

    //EDITTEXT
    private lateinit var edCorreo: EditText
    private lateinit var edContrasenna: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_in_inicio_sesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents() {
        btnIngresar = findViewById(R.id.btnIngresar_IN1)
        btnCancelar = findViewById(R.id.btnCancelar_IN1)

        edCorreo = findViewById(R.id.edUsuario_IN)
        edContrasenna = findViewById(R.id.edContrasenna_IN1)
    }

    private fun initListeners() {
        btnIngresar.setOnClickListener{fun_ingresar()}
        btnCancelar.setOnClickListener{fun_cancelar()}
    }

    private fun fun_ingresar() {
        val usuarios = GP_Procedures.get_usuario()
        var encontrado = false
        for (e in usuarios){
            println()
            if(e.email.equals(edCorreo.text.toString())){
                if (e.contrasenna.equals(edContrasenna.text.toString()) || e.contrasenna.equals(null)){
                    encontrado = true
                    GP_VariableGlobales.userNombre = e.nombre
                    GP_VariableGlobales.userCedula = e.cedula
                    val intent = Intent(this, PaginaPrincipal::class.java)
                    startActivity(intent)
                    finish()
                    break
                }
            }
        }
        if(!encontrado){
            Toast.makeText(this, "Correo no encontrado", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fun_cancelar() {
        finish()
    }
}