package com.example.setion_ap.APP.GestionProyectos.Creacion_De_Proyectos

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
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GP_CreacionProyectos : AppCompatActivity() {
    //VARIABLES GLOBALES
    val LIM_INF_ESTADOPROYECT: Int = 1
    val LIM_SUP_ESTADOPROYECT: Int = 8

    //BUTTONS
    private lateinit var btnCancelar: Button
    private lateinit var btnGuardar: Button
    private lateinit var btnHistorialDeCambios: Button
    private lateinit var btnAnadirTarea: Button
    private lateinit var imgLupa: ImageView
    private lateinit var imgCalendario: ImageView

    //TEXTVIEW EDITTEXT
    private lateinit var edNombre: EditText
    private lateinit var edRecursosNecesarios: EditText
    private lateinit var edPresupuesto: EditText
    private lateinit var tvColaboradores: TextView
    private lateinit var edEstadoDeProyecto: EditText
    private lateinit var edDescripcion: EditText
    private lateinit var tvFechaDeInicio: TextView
    private lateinit var edResponsable: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gp_creacion_proyectos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        //BUTTONS
        btnHistorialDeCambios=findViewById(R.id.btnHistorialDeCambios_GPCREACIONPROYECTOS)
        btnGuardar=findViewById(R.id.btnGuardar_GPCREACIONPROYECTOS)
        btnCancelar=findViewById(R.id.btnCancelar_GPCREACIONPROYECTOS)
        btnAnadirTarea=findViewById(R.id.btnAnadirTarea_GPCREACIONPROYECTOS)

        //IMAGES
        imgLupa=findViewById(R.id.imgLUPA_GPCREACIONPROYECTOS)
        imgCalendario=findViewById(R.id.imgCALENDAR_GPCREACIONPROYECTOS)

        //TEXTVIEW
        edNombre = findViewById(R.id.edNombre_GPCREACIONPROYECTOS)
        edRecursosNecesarios = findViewById(R.id.edRecursosNecesarios_GPCREACIONPROYECTOS)
        edPresupuesto = findViewById(R.id.edPresupuesto_GPCREACIONPROYECTOS)
        tvColaboradores = findViewById(R.id.edColaboradores_GPCREACIONPROYECTOS)
        edEstadoDeProyecto = findViewById(R.id.edEstadoDelProyecto_GPCREACIONPROYECTOS)
        edDescripcion = findViewById(R.id.edDescripcion_GPCREACIONPROYECTOS)
        tvFechaDeInicio = findViewById(R.id.tvFechaDeInicio_GPCREACIONPROYECTOS)
        edResponsable = findViewById(R.id.edResponsable_GPCREACIONPROYECTOS)
    }

    private fun initListeners() {
        //BOTONES
        btnCancelar.setOnClickListener { (finish()) }
        btnGuardar.setOnClickListener {
            fun_GuardarDatos()
            finish()
        }
        btnHistorialDeCambios.setOnClickListener { fun_HistorialDeCambios() }
        btnAnadirTarea.setOnClickListener { fun_AnadirTarea() }
        //IMAGENES
        imgLupa.setOnClickListener { fun_AnadirColaborador() }
        imgCalendario.setOnClickListener { fun_AnadirFecha() }
    }

    private fun fun_AnadirColaborador() {
        intent = Intent(this, GP_AnadirColaborador::class.java)
        startForResultColaboradores.launch(intent)
    }

    private fun fun_GuardarDatos() {
        if(edNombre.text.isNotEmpty()&&
            edRecursosNecesarios.text.isNotEmpty()&&
            edPresupuesto.text.isNotEmpty()&&
            tvColaboradores.text.isNotEmpty()&&
            edEstadoDeProyecto.text.isNotEmpty()&&
            edDescripcion.text.isNotEmpty()&&
            tvFechaDeInicio.text.isNotEmpty()&&
            edResponsable.text.isNotEmpty()){

            if(edEstadoDeProyecto.text.toString().toInt()<LIM_INF_ESTADOPROYECT
                || edEstadoDeProyecto.text.toString().toInt()>LIM_SUP_ESTADOPROYECT) {
                edEstadoDeProyecto.setText("4")
            }

            GP_Procedures.insertarProyecto(edNombre.text.toString(),
                edPresupuesto.text.toString().toDouble(),
                edEstadoDeProyecto.text.toString().toInt(),
                edDescripcion.text.toString(),
                convertirStringADate(tvFechaDeInicio.text.toString()),
                edResponsable.text.toString(), edRecursosNecesarios.text.toString(),this)
        }else
        {
            Toast.makeText(this, "No puede haber espacios sin rellenar", Toast.LENGTH_LONG).show()
        }

    }

    private fun fun_HistorialDeCambios() {
        intent = Intent(this, GP_HistorialDeCambios::class.java)
        startActivity(intent)
    }

    private fun fun_AnadirTarea() {
        intent = Intent(this, GP_AnadirTareas::class.java)
        startActivity(intent)
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
                tvFechaDeInicio.text = fechaSeleccionada
            },
            año,
            mes,
            día
        )
        datePickerDialog.show()
    }


    //FUNCIONES EXTRAS

    //Función para convertir string a fecha
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