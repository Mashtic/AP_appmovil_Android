package com.example.setion_ap.APP.GestionColaboradores.Asignaciones

import android.database.SQLException
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.setion_ap.Procedures.GP_Procedures
import com.example.setion_ap.R

class EliminarColaborador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar_colaborador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Obtiene la cédula del colaborador a eliminar (ejemplo: cedulaColaborador)
        val cedulaColaborador = 123456789

        try {
            GP_Procedures.update_eliminarColabadorDeProyecto(cedulaColaborador)
            Toast.makeText(this, "Colaborador eliminado correctamente", Toast.LENGTH_SHORT).show()
        } catch (ex: SQLException) {
            println("Error: $ex")
            Toast.makeText(this, "Error al eliminar colaborador", Toast.LENGTH_SHORT).show()
        }

        finish() // Cerrar la actividad después de eliminar el colaborador
    }
}
