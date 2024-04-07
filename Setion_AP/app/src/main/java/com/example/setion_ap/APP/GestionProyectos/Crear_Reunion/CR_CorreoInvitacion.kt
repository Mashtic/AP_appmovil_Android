package com.example.setion_ap.APP.GestionProyectos.Crear_Reunion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class CR_CorreoInvitacion : AppCompatActivity() {
    //BUTTONS
    private lateinit var btnAtras: Button
    private lateinit var btnEnviar: Button
    private lateinit var edCorreo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cr_correo_invitacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edTema: String = intent.extras?.getString("edTema").orEmpty()
        val tvFecha: String = intent.extras?.getString("tvFecha").orEmpty()
        val edMedio: String = intent.extras?.getString("edMedio").orEmpty()

        initComponents()
        initListeners()
        fun_correoPorDefault(edTema, edMedio, tvFecha)
    }

    private fun fun_correoPorDefault(edTema : String, edMedio : String, tvFecha : String) {
        edCorreo.setText(GP_VariableGlobales.get_msjCorreoPorDefault(edMedio, tvFecha, "12:00pm", "Alto"))
    }

    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_CRCORREOINVITACION)
        btnEnviar = findViewById(R.id.btnEnviar_CRCORREOINVITACION)
        edCorreo = findViewById(R.id.edCorreo_CRCORREOINVITACION)
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
        btnEnviar.setOnClickListener{finish()}
    }
}