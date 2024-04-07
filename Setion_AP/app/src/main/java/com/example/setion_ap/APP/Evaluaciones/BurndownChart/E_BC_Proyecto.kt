package com.example.setion_ap.APP.Evaluaciones.BurndownChart

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.echo.holographlibrary.Line
import com.echo.holographlibrary.LineGraph
import com.echo.holographlibrary.LinePoint
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.R
import kotlin.random.Random


class E_BC_Proyecto : AppCompatActivity() {
    //LISTA DE TAREAS
    private lateinit var listaTareas: ArrayList<vProyectoTareas>
    private lateinit var btnAtras: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ebc_proyecto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()

        val proyectId =
            intent.extras?.getInt("proyectId")//Recibe el idProyect para buscar las tareas de ese proyecto

        //Imprime las tareas en pantalla
        if (proyectId != null) {
            listaTareas = GP_Procedures.get_ProyectoTareas_fromAProyect(proyectId)
            fun_graficarLineas(listaTareas)
        }
    }

    private fun initComponents() {
        btnAtras = findViewById<Button>(R.id.btnAtras_BP)


    }

    private fun initListeners() {
        btnAtras.setOnClickListener { fun_atras() }

    }

    private fun fun_atras() {
        intent = Intent(this, E_BurndownCharts::class.java)
        startActivity(intent)
    }

    private fun fun_graficarLineas(lTareas: ArrayList<vProyectoTareas>) {

        val lineaReal = Line()
        var tiempoProyecto = Random.nextInt(13, 30)
        var cantStories = 0
        var tiempoF = 0
        var lineaIdeal = Line()
        val p = LinePoint()
        val grafica = findViewById<View>(R.id.grafica_BP) as LineGraph
        for (e in lTareas) {
            cantStories = cantStories + e.storyPoint
        }
        lineaIdeal.addPoint(LinePoint(0.0, cantStories.toDouble()))
        lineaIdeal.addPoint(LinePoint(tiempoProyecto.toDouble(), 0.0))
        lineaIdeal.color = Color.parseColor("#024A86")

        for (tareaF in lTareas) {
            if (tareaF.nombEstadoTarea.equals("Finalizado")) {
                tiempoF = Random.nextInt(1, tiempoProyecto)
                lineaReal.addPoint(
                    LinePoint(
                        tiempoF.toDouble(),
                        cantStories.toDouble()
                    )
                )
                cantStories = cantStories - tareaF.storyPoint
                tiempoProyecto = tiempoProyecto - tiempoF

            }
        }
        lineaReal.color = Color.parseColor("#6DC36D")
        grafica.addLine(lineaIdeal)
        grafica.addLine(lineaReal)
    }
}