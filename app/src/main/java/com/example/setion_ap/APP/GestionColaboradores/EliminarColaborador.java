package com.example.setion_ap.APP.GestionColaboradores;

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.R


class EliminarColaborador : AppCompatActivity() {
    private lateinit var btnAtras: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
        }
        initComponents()
        initListeners()
        }

private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_EliminarColaborador)
        createButtonsForColaboradores()
        }

private fun initListeners() {
        btnAtras.setOnClickListener{finish()}
        }

private fun createButtonsForColaboradores() {
        val listaNombresColaboradores = getNombresColaboradores() // Obtener nombres de colaboradores disponibles para eliminar

        val layoutBotones = findViewById<LinearLayout>(R.id.layoutBotonesColaboradores)
        val layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
        )

        for (nombreColaborador in listaNombresColaboradores) {
        val button = Button(this)
        button.text = nombreColaborador
        button.layoutParams = layoutParams

        // Agregar lógica para eliminar el colaborador al presionar el botón
        button.setOnClickListener {
        // Lógica para eliminar el colaborador
        println("Eliminar colaborador: $nombreColaborador")
        }

        layoutBotones.addView(button)
        }
        }

private fun getNombresColaboradores(): List<String> {
        // Aquí debes implementar la lógica para obtener los nombres de los colaboradores disponibles para eliminar
        // Por ejemplo, podrías tener una función en tu clase GP_Procedures que devuelva esta lista
        // Esta es solo una representación de cómo podrías hacerlo
        return listOf("Nombre Colaborador 1", "Nombre Colaborador 2", "Nombre Colaborador 3")
        }
        }
