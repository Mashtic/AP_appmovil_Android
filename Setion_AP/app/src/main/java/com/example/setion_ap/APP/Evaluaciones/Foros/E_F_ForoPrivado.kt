package com.example.setion_ap.APP.Evaluaciones.Foros

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R
import com.example.setion_ap.VariableGlobales.GP_VariableGlobales

class E_F_ForoPrivado : AppCompatActivity() {
    private lateinit var btnAtras: Button
    private lateinit var btnComentar: Button

    //TEXTVIEW
    private lateinit var tvForoGeneral: TextView
    private var idForo: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ef_foro_privado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        if(GP_VariableGlobales.get_proyectoAsignado()!=null) {
            fun_foroAsignado()
            cargarComentarios()
        } else {
            tvForoGeneral.setText("No está asignado a ningún proyecto")
        }
    }


    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_EFPRIVADO)
        btnComentar = findViewById(R.id.btnComentar_EFPRIVADO)
        tvForoGeneral = findViewById(R.id.tvForo_EFPRIVADO)
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{ finish() }
        btnComentar.setOnClickListener{
            if(idForo!=null){
                funComentar()
            } else{
                Toast.makeText(this, "No se puede comentar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fun_foroAsignado() {
        val Foros = GP_Procedures.get_foros()
        for (e in Foros){
            if(e.proyectoId == GP_VariableGlobales.idProyecto){
                idForo = e.idForo
                break
            }
        }
    }

    private fun funComentar() {
        if(idForo!=null){
            Comentar(this){comentario ->
                val com = comentario
                println(com)
                try {
                    val primerForo = GP_Procedures.get_foros().first()
                    //SOLO DEBO DE CAMBIAR IDFORO POR EL
                    GP_Procedures.set_agregarMensajeEnForo(idForo!!, GP_VariableGlobales.userCedula, com, this)
                    cargarComentarios()

                    /*
                    GP_Procedures.set_agregarMensajeEnForo(idForo, "11111111", com, this)
                    cargarComentarios()*/
                } catch (e : Exception){
                    Toast.makeText(this, "Erro al comentar", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            tvForoGeneral.setText("No hay foros disponibles")
        }
    }

    fun Comentar(contexto: Context, listener: (String) -> Unit) {
        val editText = EditText(contexto)
        val dialog = AlertDialog.Builder(contexto)
            .setTitle("Escribe tu comentario: ")
            .setView(editText)
            .setPositiveButton("Guardar") { dialog, _ ->
                val nota = editText.text.toString()
                listener(nota)
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }


    //La funcion del
    @SuppressLint("SetTextI18n")
    private fun cargarComentarios() {
        tvForoGeneral.text = ""
        try {
            val comentariosForo = GP_Procedures.get_foros_comentatios()
            for (e in comentariosForo){
                println(e.foroId)
                if(e.foroId == idForo){
                    tvForoGeneral.text = tvForoGeneral.text.toString() +
                            e.autor + " - " + e.contenido+"\n\n"
                }
            }
            if(tvForoGeneral.text.isEmpty()){
                tvForoGeneral.text = "El foro no tiene comentarios"
            }
        } catch (e : Exception){
            Toast.makeText(this, "Nada por cargar", Toast.LENGTH_SHORT).show()
        }
    }
}