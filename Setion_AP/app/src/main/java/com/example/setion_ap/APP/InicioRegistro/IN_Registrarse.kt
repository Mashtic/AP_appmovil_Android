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

class IN_Registrarse : AppCompatActivity() {
    private lateinit var btnAceptar: Button
    private lateinit var btnCancelar: Button

    //EDITTEXT
    private lateinit var edNombre: EditText
    private lateinit var edApellidos: EditText
    private lateinit var edCedula: EditText
    private lateinit var edCorreo: EditText
    private lateinit var edTelefono: EditText
    private lateinit var edContrasenna: EditText
    private lateinit var edConfContrasenna: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_in_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents() {
        btnAceptar = findViewById(R.id.btnAceptar_IN2)
        btnCancelar = findViewById(R.id.btnCancelar_IN2)

        edNombre = findViewById(R.id.edNombre_IN2)
        edApellidos = findViewById(R.id.edApellidos_IN2)
        edCedula = findViewById(R.id.edCedula_IN2)
        edCorreo = findViewById(R.id.edCorreo_IN2)
        edTelefono = findViewById(R.id.edTelefono_IN2)
        edContrasenna = findViewById(R.id.edContrasenna_IN2)
        edConfContrasenna = findViewById(R.id.edConfirmarContrasenna_IN2)
    }

    private fun initListeners() {
        btnAceptar.setOnClickListener{fun_ingresar()}
        btnCancelar.setOnClickListener{finish()}
    }

    private fun fun_ingresar() {
        if (edNombre.text.isNotEmpty() && edApellidos.text.isNotEmpty() &&
            edCorreo.text.isNotEmpty() && edContrasenna.text.isNotEmpty() &&
            edConfContrasenna.text.isNotEmpty() && edTelefono.text.isNotEmpty() &&
            edCedula.text.isNotEmpty()){
            if (edContrasenna.text.toString().equals(edConfContrasenna.text.toString())){
                if(GP_Procedures.set_insertarColaborador(edCedula.text.toString(),
                    edNombre.text.toString() + " " + edApellidos.text.toString(),
                    edCorreo.text.toString(),
                    1,
                    edTelefono.text.toString(),
                    edContrasenna.text.toString(), this))
                {
                    GP_VariableGlobales.userNombre = edNombre.text.toString() + " " + edApellidos.text.toString()
                    GP_VariableGlobales.userCedula = edCedula.text.toString()
                    val intent = Intent(this, PaginaPrincipal::class.java)
                    startActivity(intent)
                }else{
                    finish()
                }

            }else{
                Toast.makeText(this, "Inconsistencia en contraseña", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "Datos incompletos", Toast.LENGTH_SHORT).show()
        }
    }
}