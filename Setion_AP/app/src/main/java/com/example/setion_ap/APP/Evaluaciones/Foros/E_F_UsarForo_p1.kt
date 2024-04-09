package com.example.setion_ap.APP.Evaluaciones.Foros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R

class E_F_UsarForo_p1 : AppCompatActivity() {

    private lateinit var btnAtras:Button
    private lateinit var btnForoGeneral:Button
    private lateinit var btnForoPrivado:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ef_usar_foro_p1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    private fun initComponents(){
        btnAtras = findViewById(R.id.btnAtras_UF)
        btnForoGeneral = findViewById(R.id.btnForoGeneral_UF)
        btnForoPrivado = findViewById(R.id.btnForoPrivado_UF)
    }
    private fun initListeners(){
        btnAtras.setOnClickListener { fun_atras() }
        btnForoGeneral.setOnClickListener{fun_foroGeneral()}
        btnForoPrivado.setOnClickListener{fun_foroPrivado()}
    }

    private fun fun_atras(){
        finish()
    }

    private fun fun_foroGeneral() {
        val intent = Intent(this, E_F_ForoGeneral::class.java)
        startActivity(intent)
    }

    private fun fun_foroPrivado() {
        val intent = Intent(this, E_F_ForoPrivado::class.java)
        startActivity(intent)
    }
}