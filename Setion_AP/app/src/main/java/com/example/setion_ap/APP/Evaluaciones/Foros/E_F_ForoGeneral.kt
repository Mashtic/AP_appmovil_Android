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

class E_F_ForoGeneral : AppCompatActivity() {
    private lateinit var btnAtras: Button
    private lateinit var btnComentar: Button

    //TEXTVIEW
    private lateinit var tvForoGeneral: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ef_foro_general)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        cargarComentarios()
    }



    private fun initComponents() {
        btnAtras = findViewById(R.id.btnAtras_EFGENERAL)
        btnComentar = findViewById(R.id.btnComentar_EFGENERAL)

        tvForoGeneral = findViewById(R.id.tvForo_EFGENERAL)
    }

    private fun initListeners() {
        btnAtras.setOnClickListener{ finish() }
        btnComentar.setOnClickListener{ funComentar() }
    }

    private fun funComentar() {
        Comentar(this){comentario ->
            val com = comentario
            println(com)
            try {
                val primerForo = GP_Procedures.get_foros().first()
                GP_Procedures.set_agregarMensajeEnForo(primerForo.idForo, "11111111", com, this)
                cargarComentarios()
            } catch (e : Exception){
                Toast.makeText(this, "Erro al comentar", Toast.LENGTH_SHORT).show()
            }

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


    @SuppressLint("SetTextI18n")
    private fun cargarComentarios() {
        tvForoGeneral.text = ""
       try {
           val foroGeneral = GP_Procedures.get_foros().first()
           val comentariosForo = GP_Procedures.get_foros_comentatios()
           for (e in comentariosForo){
               println(e.foroId)
               if(e.foroId == foroGeneral.idForo){
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