package com.example.setion_ap.APP.Evaluaciones.Foros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.APP.Evaluaciones.E_CreacionDeForos
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class E_F_CrearForo : AppCompatActivity() {

    private lateinit var btnGuardar:Button
    private lateinit var btnCancelar:Button

    //EDIT TEXT
    private lateinit var edNombre:EditText
    private lateinit var edDescripcionForo:EditText
    private lateinit var edProyectoID:EditText
    private lateinit var edContenido:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ef_crear_foro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if(GP_VariableGlobales.get_proyectoAsignado()!=null) {
            if (noHayForos()) {
                initComponents()
                initListeners()
            } else {
                Toast.makeText(this, "Foro ya disponible", Toast.LENGTH_LONG).show()
                finish()
            }
        }else{
            println(GP_VariableGlobales.get_proyectoAsignado().toString() + "")
            Toast.makeText(this, "Proyecto no asignado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initComponents(){
        btnGuardar= findViewById<Button>(R.id.btnGuardar_CF)
        btnCancelar = findViewById<Button>(R.id.btnCancelar_CF)

        //EDIT TEXT
        edNombre = findViewById(R.id.edNombre_CF)
        edDescripcionForo = findViewById(R.id.edDescripcionForo_CF)
        edProyectoID = findViewById(R.id.edProyectoID_CF)
        edContenido = findViewById(R.id.edContenido_CF)
    }
    private fun initListeners(){
        btnGuardar.setOnClickListener { fun_guardar() }
        btnCancelar.setOnClickListener { fun_cancelar() }
    }
    private fun fun_guardar(){
        if(edNombre.text.isNotEmpty() && edDescripcionForo.text.isNotEmpty() && edContenido.text.isNotEmpty()){
            GP_Procedures.set_insertarForo(edNombre.text.toString(),
                edDescripcionForo.text.toString(),
                GP_VariableGlobales.get_proyectoAsignado(),
                edContenido.text.toString(),
                this)// De momento, idPoryecto no habilitado
            finish()
        }else{
            Toast.makeText(this, "Debe llenar espacios", Toast.LENGTH_LONG).show()
        }
    }

    //Valida si en un proyecto hay foros, si no hay foros entonces devuelve true
    private fun noHayForos(): Boolean {
        val Foros = GP_Procedures.get_foros()
        for (e in Foros){
            if(e.proyectoId == GP_VariableGlobales.idProyecto){
                return false
            }
        }
        return true
    }

    private fun fun_cancelar(){
        finish()
    }
}