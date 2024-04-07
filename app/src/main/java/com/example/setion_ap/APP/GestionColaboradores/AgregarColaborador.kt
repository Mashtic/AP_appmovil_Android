package com.example.setion_ap.APP.GestionColaboradores

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
    private lateinit var btnEliminar: Button

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
        btnEliminar = findViewById(R.id.btnEliminarColaborador)
        createButtonsForColaboradores()
    }

    private fun initListeners() {
        btnAtras.setOnClickListener { finish() }
        btnEliminar.setOnClickListener {
            // Aquí debes implementar la lógica para eliminar el colaborador seleccionado
            // Por ejemplo, si tienes una lista de colaboradores, puedes eliminar el colaborador en la posición seleccionada
            // listaColaboradores.removeAt(posicionSeleccionada)
            // Después, puedes actualizar la interfaz de usuario para reflejar los cambios
            createButtonsForColaboradores()
        }
    }

    private fun createButtonsForColaboradores() {
        val listaNombresColaboradores = getNombresColaboradores() // Obtener nombres de colaboradores disponibles para eliminar

        val layoutBotones = findViewById<LinearLayout>(R.id.layoutBotonesColaboradores)
        layoutBotones.removeAllViews() // Limpiar los botones anteriores

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

        return listOf("Nombre Colaborador 1", "Nombre Colaborador 2", "Nombre Colaborador 3")
    }
}
