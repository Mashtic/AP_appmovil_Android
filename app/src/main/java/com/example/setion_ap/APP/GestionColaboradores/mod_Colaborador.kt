package com.example.setion_ap.APP.GestionColaboradores

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R
import com.example.setion_ap.Procedures.GP_Procedures

class mod_Colaborador : AppCompatActivity() {
    // Edit Texts
    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etDepartamento: EditText

    // Buttons
    private lateinit var btnAceptar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mod_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()

        // Se llama a la función para crear los botones dinámicamente
        createButtonsForColaboradores()
    }

    private fun initComponents() {
        etNombre = findViewById(R.id.etNombre_GestionColaboradores)
        etCorreo = findViewById(R.id.etCorreo_GestionColaboradores)
        etTelefono = findViewById(R.id.etTelefono_GestionColaboradores)
        etDepartamento = findViewById(R.id.etDepartamento_GestionColaboradores)

        btnAceptar = findViewById(R.id.btnAceptar_GestionColaboradores)
        btnCancelar = findViewById(R.id.btnCancelar_GestionColaboradores)
    }

    private fun initListeners() {
        btnAceptar.setOnClickListener {
            val nombreColaborador = etNombre.text.toString()
            val emailNuevo = etCorreo.text.toString()
            val telefonoNuevo = etTelefono.text.toString()
            val departamentoNuevo = etDepartamento.text.toString().toIntOrNull()

            if (nombreColaborador.isNotBlank()) {
                updateColaborador(nombreColaborador, emailNuevo, telefonoNuevo, departamentoNuevo)
            } else {
                println("Nombre de colaborador inválido")
            }
        }
        btnCancelar.setOnClickListener { finish() }
    }

    private fun createButtonsForColaboradores() {
        val listaNombresColaboradores = getNombresColaboradores() // Función que obtiene los nombres de los colaboradores de tu base de datos

        val layoutBotones = findViewById<LinearLayout>(R.id.layoutBotonesColaboradores)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        for (nombreColaborador in listaNombresColaboradores) {
            val button = Button(this)
            button.text = nombreColaborador
            button.layoutParams = layoutParams


            button.setOnClickListener {
                // duda
                etNombre.setText(nombreColaborador)
            }

            layoutBotones.addView(button)
        }
    }


    private fun updateColaborador(nombreColaborador: String, emailNuevo: String, telefonoNuevo: String, departamentoNuevo: Int?) {
        GP_Procedures.update_colaborador(nombreColaborador, emailNuevo, telefonoNuevo, departamentoNuevo)

    }
}
