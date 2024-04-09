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
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
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

        val grafica3 = findViewById<View>(R.id.grafica_BP) as LineChart
        var tiempoProyecto = Random.nextInt(13, 30)
        var cantStories = 0
        var tiempoF = 0
        var arrayLineaIdeal= ArrayList<Entry>()
        var arrayLineaReal=ArrayList<Entry>()

        for (e in lTareas) {
            cantStories = cantStories + e.storyPoint
        }
        arrayLineaIdeal.add(Entry(0f, cantStories.toFloat()))
        arrayLineaIdeal.add(Entry(tiempoProyecto.toFloat(),0f))

        val lineaIdeal =LineDataSet(arrayLineaIdeal,"Estimado")
        lineaIdeal.lineWidth= 3F



        arrayLineaReal.add(Entry(0f, cantStories.toFloat()))
        var lTiempo=0
        for (tareaF in lTareas) {
            if (tareaF.nombEstadoTarea.equals("Por hacer"))
            {

            }else if(tareaF.nombEstadoTarea.equals("En progreso")){

            }else{
                tiempoF = Random.nextInt(1, tiempoProyecto-1)
                lTiempo=lTiempo+tiempoF
                arrayLineaReal.add(Entry(lTiempo.toFloat(),cantStories.toFloat()))
                cantStories = cantStories - tareaF.storyPoint
                tiempoProyecto = tiempoProyecto - tiempoF
            }
        }
        val lineaReal = LineDataSet(arrayLineaReal, "Real")
        lineaReal.lineWidth=3f
        lineaReal.setColors(Color.RED)
        val lineIdealData = LineData(listOf( lineaIdeal,lineaReal))
        grafica3.data=lineIdealData
        grafica3.invalidate()
    }
}