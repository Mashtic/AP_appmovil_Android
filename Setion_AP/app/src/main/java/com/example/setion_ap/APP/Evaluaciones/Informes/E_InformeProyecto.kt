package com.example.setion_ap.APP.Evaluaciones.Informes

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
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class E_InformeProyecto : AppCompatActivity() {
    private lateinit var btnAtras:Button

    //LISTA DE TAREAS
    private lateinit var listaTareas:ArrayList<vProyectoTareas>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_einforme_proyecto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()

        val proyectId = intent.extras?.getInt("proyectId")//Recibe el idProyect para buscar las tareas de ese proyecto

        //Imprime las tareas en pantalla
        if(proyectId!=null){
            listaTareas = GP_Procedures.get_ProyectoTareas_fromAProyect(proyectId)
            fun_graficarBarras(listaTareas)
        }
    }

    private fun initComponents(){
        btnAtras = findViewById<Button>(R.id.btnAtras_IP)

    }
    private fun initListeners(){
        btnAtras.setOnClickListener { fun_atras() }

    }

    private fun fun_atras(){
        intent = Intent(this, E_InformeGeneral::class.java)
        startActivity(intent)
    }


    private fun fun_graficarBarras(lTareas:ArrayList<vProyectoTareas>){
        var cantPH =0.0
        var cantP= 0.0
        var cantF=0.0
        for (e in lTareas){
            if (e.nombEstadoTarea.equals("Por hacer"))
            {
                cantPH=cantPH+1
            }else if(e.nombEstadoTarea.equals("En progreso")){
                cantP=cantP+1
            }else{
                cantF=cantF+1
            }
        }
        val listaDatosPH:ArrayList<BarEntry> = ArrayList()
        val listaDatosP:ArrayList<BarEntry> = ArrayList()
        val listaDatosF:ArrayList<BarEntry> = ArrayList()
        listaDatosPH.add(BarEntry(1f,cantPH.toFloat()))
        listaDatosP.add(BarEntry(2f,cantP.toFloat()))
        listaDatosF.add(BarEntry(3f,cantF.toFloat()))

        val barDataSetPH = BarDataSet(listaDatosPH,"Por Hacer")
        val barDataSetP = BarDataSet(listaDatosP,"En progreso")
        val barDataSetF = BarDataSet(listaDatosF,"Finalizado")

        barDataSetPH.setColors(Color.parseColor("#EF280F"))
        barDataSetPH.valueTextColor=Color.BLACK

        barDataSetP.setColors(Color.parseColor("#02AC66"))
        barDataSetP.valueTextColor=Color.BLACK

        barDataSetF.setColors(Color.parseColor("#E36B2C"))
        barDataSetF.valueTextColor=Color.BLACK

        val barDataPH =BarData(barDataSetPH)
        val barDataP =BarData(barDataSetP)
        val barDataF =BarData(barDataSetF)
        val grafica = findViewById<View>(R.id.grafica_IP) as BarChart
        grafica.data=barDataPH
        grafica.data.addDataSet(barDataSetP)
        grafica.data.addDataSet(barDataSetF)

        grafica.animateY(2000)
    }
}