package com.example.setion_ap.APP.GestionProyectos.Crear_Reunion

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.GestionProyectos.Creacion_De_Proyectos.GP_AnadirColaborador
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CR_NuevaReunion : AppCompatActivity() {
    //BUTTONS
    private lateinit var btnCancelar: Button
    private lateinit var btnAgendar: Button
    private lateinit var btnEditarCorreo: Button
    private lateinit var imgLupa: ImageView
    private lateinit var imgCalendario: ImageView

    //EDIT TEXT
    private lateinit var edTema: EditText
    private lateinit var tvFecha: TextView
    private lateinit var edMedio: EditText
    private lateinit var tvColaboradores: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cr_nueva_reunion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnCancelar = findViewById(R.id.btnCancelar_CRCREARREUNION)
        btnAgendar = findViewById(R.id.btnAgendar_CRCREARREUNION)
        btnEditarCorreo = findViewById(R.id.btnEditarCorreo_CRCREARREUNION)

        imgLupa = findViewById(R.id.imgLUPA_CRCREARREUNION)
        imgCalendario = findViewById(R.id.imgCALENDAR_CRCREARREUNION)

        edTema = findViewById(R.id.edTema_CRCREARREUNION)
        tvFecha = findViewById(R.id.tvFecha_CRCREARREUNION)
        edMedio = findViewById(R.id.edMedio_CRCREARREUNION)
        tvColaboradores = findViewById(R.id.tvColaboradores_CRCREARREUNION)
    }

    private fun initListeners() {
        btnCancelar.setOnClickListener{finish()}
        btnAgendar.setOnClickListener{fun_Agendar()}
        btnEditarCorreo.setOnClickListener{fun_AnadirReunion()}
        imgLupa.setOnClickListener{fun_AnadirColaborador()}
        imgCalendario.setOnClickListener { fun_AnadirFecha() }
    }

    private fun fun_Agendar() {
        if(edMedio.text.isNotEmpty() && edTema.text.isNotEmpty() && tvFecha.text.isNotEmpty()){
            GP_Procedures.set_crearReunion(convertirStringADate(tvFecha.text.toString()),
                Time.valueOf("15:00:00"),
                edTema.text.toString(),
                edMedio.text.toString())
        }else{
            Toast.makeText(this, "Espacios vacíos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fun_AnadirColaborador() {
        intent = Intent(this, GP_AnadirColaborador::class.java)
        startForResultColaboradores.launch(intent)
    }

    private fun fun_AnadirReunion() {
        if (edMedio.text.isNotEmpty() && edTema.text.isNotEmpty() && tvFecha.text.isNotEmpty()) {

            intent = Intent(this, CR_CorreoInvitacion::class.java)
            intent.putExtra("edMedio", edMedio.text.toString())
            intent.putExtra("edTema", edTema.text.toString())
            intent.putExtra("tvFecha", tvFecha.text.toString())
            startActivity(intent)
        }else{
            Toast.makeText(this, "Espacios vacíos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fun_AnadirFecha() {
        val calendario = Calendar.getInstance()
        val año = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val día = calendario.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                // La fecha seleccionada por el usuario se muestra en el TextView
                val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
                tvFecha.text = fechaSeleccionada
            },
            año,
            mes,
            día
        )
        datePickerDialog.show()
    }

    private fun convertirStringADate(fechaTexto: String): java.sql.Date {
        // Definir el formato de fecha deseado
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return try {
            // Parsear el texto de fecha al formato definido
            val fecha = formato.parse(fechaTexto)
            println(java.sql.Date(fecha.time))

            // Convertir el objeto Date a java.sql.Date
            java.sql.Date(fecha.time)
        } catch (ex: Exception) {
            // Manejar cualquier excepción que pueda ocurrir durante la conversión
            ex.printStackTrace()
            java.sql.Date(System.currentTimeMillis())
        }
    }

    private val startForResultColaboradores = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
        tvColaboradores.setText("")
        for(e in GP_VariableGlobales.listaColaboradoresAnadidos){
            tvColaboradores.setText(tvColaboradores.text.toString() + e.nombreCompleto + "\n")
        }
    }
}