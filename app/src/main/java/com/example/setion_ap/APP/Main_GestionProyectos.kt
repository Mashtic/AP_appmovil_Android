package com.example.setion_ap.APP

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.GestionProyectos.Consulta_De_Proyectos.CP_Consulta_De_Proyectos
import com.example.setion_ap.APP.GestionProyectos.Crear_Reunion.CR_NuevaReunion
import com.example.setion_ap.APP.GestionProyectos.Modificacion_De_Informacion.MI_ModificarTarea
import com.example.setion_ap.APP.GestionProyectos.Creacion_De_Proyectos.GP_CreacionProyectos
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import java.sql.Date
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Main_GestionProyectos : AppCompatActivity() {
    private lateinit var btnCreacionDeProyectos:Button
    private lateinit var btnConsultaDeProyectos:Button
    private lateinit var btnModificacionDeInformacion:Button
    private lateinit var btnCreacionDeReuniones:Button
    private lateinit var btnAtras:Button


    override fun onCreate(savedInstanceState: Bundle?) {



        val verifica = GP_Procedures.verificarCredenciales("jeisonfo@gmail.com", "advs")
        println("Mira::: $verifica")

        //val modColPry2 = GP_Procedures.get_tareasPorEstadoYEncargado(3
          //  , null)
        //val modColPry3 = GP_Procedures.get_tareasPorEstadoYEncargado(null, null)
        //println("Mira::: $updC("122342343", null, null, 2)")
        //println("Mira::: $foro")
        //println("Mira::: $modColPry3")

        //GP_Procedures.set_colaboradorProyecto(122342343, 4)

        //GP_Procedures.set_tareaProyecto(2, "CSS cambiado", 5, 3, "1234567890")


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_gestionproyectos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnCreacionDeProyectos = findViewById<Button>(R.id.btnCreacionDeProyectos_MAINGP)
        btnConsultaDeProyectos = findViewById<Button>(R.id.btnConsultaDeProyectos_MAINGP)
        btnModificacionDeInformacion= findViewById<Button>(R.id.btnModificacionDeInformacion_MAINGP)
        btnCreacionDeReuniones= findViewById<Button>(R.id.btnCreacionDeReuniones_MAINGP)
        btnAtras = findViewById<Button>(R.id.btnAtras_MAINGP)
    }

    private fun initListeners() {
        btnCreacionDeProyectos.setOnClickListener{fun_crecionDeProyectos()}
        btnConsultaDeProyectos.setOnClickListener{fun_consultaDeProyectos()}
        btnModificacionDeInformacion.setOnClickListener{fun_modificacionDeInformacion()}
        btnCreacionDeReuniones.setOnClickListener{ Companion.fun_crecionDeReuniones(this) }
        btnAtras.setOnClickListener{fun_atras()}
    }

    private fun fun_crecionDeProyectos() {
        intent = Intent(this, GP_CreacionProyectos::class.java)
        startActivity(intent)
    }

    private fun fun_consultaDeProyectos() {
        intent = Intent(this, CP_Consulta_De_Proyectos::class.java)
        startActivity(intent)
    }

    private fun fun_modificacionDeInformacion() {
        intent = Intent(this, MI_ModificarTarea::class.java)
        startActivity(intent)
    }

    private fun fun_atras() {
        println("Hola")
    }

    companion object {
        private fun fun_crecionDeReuniones(mainGestionproyectos: Main_GestionProyectos) {
            mainGestionproyectos.intent = Intent(mainGestionproyectos, CR_NuevaReunion::class.java)
            mainGestionproyectos.startActivity(mainGestionproyectos.intent)
        }
    }
}