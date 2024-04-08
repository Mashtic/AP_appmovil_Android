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
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.Procedures.vProyectoTareas
import com.example.setion_ap.R

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
        var cantPH =0
        var cantP= 0
        var cantF=0
        val barra1= Bar()
        val barra2= Bar()
        val barra3= Bar()
        val puntos= ArrayList<Bar>()

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
        barra1.color = Color.parseColor("#02AC66")
        barra1.name = "Por hacer"
        barra1.value = cantPH.toFloat()
        puntos.add(barra1)
        barra2.color = Color.parseColor("#02AC66")
        barra2.name = "En progreso"
        barra2.value = cantP.toFloat()
        puntos.add(barra2)
        barra3.color = Color.parseColor("#02AC66")
        barra3.name = "Finalizado"
        barra3.value = cantF.toFloat()
        puntos.add(barra3)

        val grafica = findViewById<View>(R.id.grafica_IP) as BarGraph
        grafica.bars= puntos
    }
}