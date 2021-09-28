package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         //HACER CAMBIO DE PANTALLA DESPUES DE 5s
        Timer().schedule(object: TimerTask()
        {
            override fun run()
            {
                val cambio = Intent(this@MainActivity , pantalla_listaDeNotas::class.java)
                startActivity(cambio)
                finish()
            }
        } , 5000 )
    }
}