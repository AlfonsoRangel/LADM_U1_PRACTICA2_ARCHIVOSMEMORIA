package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoria

import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.io.*

class formulario_nota : AppCompatActivity() {

    lateinit var cajaTitulo: EditText
    lateinit var cajaRecordatorio: EditText
    lateinit var img_telefono: RelativeLayout
    lateinit var img_sd: RelativeLayout
    lateinit var frameRojo: FrameLayout
    lateinit var frameAzul: FrameLayout
    lateinit var frameVerde: FrameLayout
    lateinit var frameMorado: FrameLayout
    var tipoMemoria = ""
    var colorNota = ""
    var esNotaNueva = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_nota)

        // ASOCIAR ELEMENTOS XML
        cajaTitulo = findViewById(R.id.cajaTitulo)
        cajaRecordatorio = findViewById(R.id.cajaRecordatorio)
        img_telefono = findViewById(R.id.opcion_memoriaInterna)
        img_sd = findViewById(R.id.opcion_memoriaExterna)
        frameRojo = findViewById(R.id.frame_rojo)
        frameAzul = findViewById(R.id.frame_azul)
        frameVerde = findViewById(R.id.frame_verde)
        frameMorado = findViewById(R.id.frame_morado)


        //EVENTO CLICK EN LA IMAGEN TELEFONO
        img_telefono.setOnClickListener {
            if( esNotaNueva == true ) {
                // COLOR DE FONDO VERDE
                img_telefono.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D93C5C17")))
                img_sd.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#003C5C17")))
                tipoMemoria = "I";
            }
        }

        //EVENTO CLICK EN LA IMAGEN SD
        img_sd.setOnClickListener {
            if( esNotaNueva == true ) {
                // COLOR DE FONDO VERDE
                img_sd.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D93C5C17")))
                img_telefono.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#003C5C17")))
                tipoMemoria = "E";
            }
        }


        // EVENTOS CLICKS EN LOS FRAMES COLORES
        frameRojo.setOnClickListener {
            if( esNotaNueva == true )
            {
                frameAzul.removeAllViews()
                frameVerde.removeAllViews()
                frameMorado.removeAllViews()
                frameRojo.removeAllViews()

                val imgOK = ImageView( this )
                frameRojo.addView( imgOK )
                imgOK.setImageResource(R.drawable.icono_ok_circular)

                val params = imgOK.layoutParams as FrameLayout.LayoutParams
                params.width = FrameLayout.LayoutParams.MATCH_PARENT
                params.height = FrameLayout.LayoutParams.MATCH_PARENT
                imgOK.layoutParams = params
                colorNota = "ROJO"
            }
        }

        frameAzul.setOnClickListener {
            if( esNotaNueva == true ) {
                frameAzul.removeAllViews()
                frameVerde.removeAllViews()
                frameMorado.removeAllViews()
                frameRojo.removeAllViews()

                val imgOK = ImageView(this)
                frameAzul.addView(imgOK)
                imgOK.setImageResource(R.drawable.icono_ok_circular)

                val params = imgOK.layoutParams as FrameLayout.LayoutParams
                params.width = FrameLayout.LayoutParams.MATCH_PARENT
                params.height = FrameLayout.LayoutParams.MATCH_PARENT
                imgOK.layoutParams = params
                colorNota = "AZUL"
            }
        }

        frameVerde.setOnClickListener {
            if( esNotaNueva == true ) {
                frameAzul.removeAllViews()
                frameVerde.removeAllViews()
                frameMorado.removeAllViews()
                frameRojo.removeAllViews()

                val imgOK = ImageView(this)
                frameVerde.addView(imgOK)
                imgOK.setImageResource(R.drawable.icono_ok_circular)

                val params = imgOK.layoutParams as FrameLayout.LayoutParams
                params.width = FrameLayout.LayoutParams.MATCH_PARENT
                params.height = FrameLayout.LayoutParams.MATCH_PARENT
                imgOK.layoutParams = params
                colorNota = "VERDE"
            }
        }

        frameMorado.setOnClickListener {
            if( esNotaNueva == true ) {
                frameAzul.removeAllViews()
                frameVerde.removeAllViews()
                frameMorado.removeAllViews()
                frameRojo.removeAllViews()

                val imgOK = ImageView(this)
                frameMorado.addView(imgOK)
                imgOK.setImageResource(R.drawable.icono_ok_circular)

                val params = imgOK.layoutParams as FrameLayout.LayoutParams
                params.width = FrameLayout.LayoutParams.MATCH_PARENT
                params.height = FrameLayout.LayoutParams.MATCH_PARENT
                imgOK.layoutParams = params
                colorNota = "MORADO"
            }
        }


        // EVENTO CLICK EN REGRESAR
        var boton_regresar: ImageView = findViewById(R.id.boton_regresar)
        boton_regresar.setOnClickListener {
            var irALista = Intent(this , pantalla_listaDeNotas::class.java)
            startActivity(irALista)
            finish()
        }



        // ============================================================================
        // ADECUAR PANTALLA
        //=============================================================================
        var img_boton: ImageView = findViewById(R.id.img_boton)
        val txt_boton: TextView = findViewById(R.id.txt_boton)
        val layout_boton: LinearLayout = findViewById(R.id.layout_boton)

        var numeroNota = intent.getIntExtra("numNota" , -1)
        if(numeroNota != -1)
        {
            // CUANDO SE DA CLIC EN UNA NOTA
            layout_boton.setBackgroundTintList(
                ColorStateList.valueOf( Color.parseColor( "#F2A46A66"))) // boton rojo
            img_boton.setImageResource(R.drawable.icono_eliminar)
            img_boton.setColorFilter( Color.parseColor( "#591915") )   // img del boton en rojo oscuro
            txt_boton.setText( "Eliminar" )
            txt_boton.setTextColor( Color.parseColor( "#591915") ) // txt del boton en rojo oscuro
            esNotaNueva = false
            cajaTitulo.setEnabled(false)
            cajaRecordatorio.setEnabled(false)

            // COLOCAR INFO DE LA NOTA
            var tituloNOTA = intent.getStringExtra("titulo")
            var recordatorioNOTA = intent.getStringExtra("recordatorio")
            var colorNOTA = intent.getStringExtra("color")
            var tipoMemoriaNOTA = intent.getStringExtra("tipoMemoria")

            cajaTitulo.setText( tituloNOTA )
            cajaRecordatorio.setText( recordatorioNOTA )
            if( tipoMemoriaNOTA == "INTERNA" )
            {
                // COLOR DE FONDO VERDE
                img_telefono.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D93C5C17")))
                img_sd.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#003C5C17")))
                tipoMemoria = "I";
            }
            else
            {
                // COLOR DE FONDO VERDE
                img_sd.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D93C5C17")))
                img_telefono.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#003C5C17")))
                tipoMemoria = "E";
            }
            when( colorNOTA )
            {
                "ROJO" ->
                {
                    val imgOK = ImageView(this)
                    frameRojo.addView(imgOK)
                    imgOK.setImageResource(R.drawable.icono_ok_circular)
                    val params = imgOK.layoutParams as FrameLayout.LayoutParams
                    params.width = FrameLayout.LayoutParams.MATCH_PARENT
                    params.height = FrameLayout.LayoutParams.MATCH_PARENT
                    imgOK.layoutParams = params
                    colorNota = "ROJO"
                }
                "AZUL" ->
                {
                    val imgOK = ImageView(this)
                    frameAzul.addView(imgOK)
                    imgOK.setImageResource(R.drawable.icono_ok_circular)
                    val params = imgOK.layoutParams as FrameLayout.LayoutParams
                    params.width = FrameLayout.LayoutParams.MATCH_PARENT
                    params.height = FrameLayout.LayoutParams.MATCH_PARENT
                    imgOK.layoutParams = params
                    colorNota = "AZUL"
                }
                "VERDE" ->
                {
                    val imgOK = ImageView(this)
                    frameVerde.addView(imgOK)
                    imgOK.setImageResource(R.drawable.icono_ok_circular)
                    val params = imgOK.layoutParams as FrameLayout.LayoutParams
                    params.width = FrameLayout.LayoutParams.MATCH_PARENT
                    params.height = FrameLayout.LayoutParams.MATCH_PARENT
                    imgOK.layoutParams = params
                    colorNota = "VERDE"
                }
                "MORADO" ->
                {
                    val imgOK = ImageView(this)
                    frameMorado.addView(imgOK)
                    imgOK.setImageResource(R.drawable.icono_ok_circular)
                    val params = imgOK.layoutParams as FrameLayout.LayoutParams
                    params.width = FrameLayout.LayoutParams.MATCH_PARENT
                    params.height = FrameLayout.LayoutParams.MATCH_PARENT
                    imgOK.layoutParams = params
                    colorNota = "MORADO"
                }
            }

            layout_boton.setOnClickListener {
                if( tipoMemoria == "I" ){ eliminarEnMemoriaInterna( numeroNota ) }
                else{ eliminarEnMemoriaExterna( numeroNota ) }
            }
        }
        else
        {
            // GUARDAR NUEVA NOTA
            layout_boton.setBackgroundTintList(
                ColorStateList.valueOf( Color.parseColor( "#F74C7E4F"))) // boton verde
            img_boton.setImageResource(R.drawable.icono_guardar)
            img_boton.setColorFilter( Color.parseColor( "#659868") )   // img del boton en verde oscuro
            txt_boton.setText( "Guardar" )
            txt_boton.setTextColor( Color.parseColor( "#659868") ) // txt del boton en verde oscuro

            layout_boton.setOnClickListener {
                if( tipoMemoria == "I" ){ guardarEnMemoriaInterna() }
                else if(tipoMemoria == "E"){ guardarEnMemoriaExterna() }
                else{ Toast.makeText(this , "DEBES ELEGIR EN DONDE LO QUIERES GUARDAR" , Toast.LENGTH_SHORT ).show() }
            }
        }
    }


    private fun eliminarEnMemoriaExterna( numero_nota: Int) {
        var continuar = true
        var numN = numero_nota
        while( continuar == true )
        {
            try
            {
                // OBTENER DATOS DE LA SIGUIENTE NOTA
                numN++
                val tarjetaSD = getExternalFilesDir(null)

                // OBTENER EL TITULO
                var archivoTitulo = "notaT" + numN.toString() + ".txt"
                val ficheroT = File(tarjetaSD?.absolutePath , archivoTitulo)
                val archivoT = BufferedReader( InputStreamReader( FileInputStream(ficheroT) ) )
                var file_titulo = ""
                var lineaT = archivoT.readLine()
                while( lineaT != null )
                {
                    file_titulo = file_titulo + lineaT + "\n"
                    lineaT = archivoT.readLine()
                }
                file_titulo = file_titulo.substring(0 , file_titulo.length-1)
                archivoT.close()

                // OBTENER EL RECORDATORIO
                var archivoRecordatorio = "notaR" + numN.toString() + ".txt"
                val ficheroR = File(tarjetaSD?.absolutePath , archivoRecordatorio)
                val archivoR = BufferedReader( InputStreamReader( FileInputStream(ficheroR) ) )
                var file_recordatorio = ""
                var lineaR = archivoR.readLine()
                while( lineaR != null )
                {
                    file_recordatorio = file_recordatorio + lineaR + "\n"
                    lineaR = archivoR.readLine()
                }
                file_recordatorio = file_recordatorio.substring(0 , file_recordatorio.length-1)
                archivoR.close()

                // OBTENER EL COLOR
                var archivoColor = "notaC" + numN.toString() + ".txt"
                val ficheroC = File(tarjetaSD?.absolutePath , archivoColor)
                val archivoC = BufferedReader( InputStreamReader( FileInputStream(ficheroC) ) )
                var file_color = ""
                var lineaC = archivoC.readLine()
                while( lineaC != null )
                {
                    file_color = file_color + lineaC + "\n"
                    lineaC = archivoC.readLine()
                }
                file_color = file_color.substring(0 , file_color.length-1)
                archivoC.close()

                // ESCRIBIR SOBRE LA NOTA ACTUAL
                // GUARDAR TITULO
                var nuevoT = "notaT" + (numN-1).toString() + ".txt"
                val ficheroNT = File(tarjetaSD?.absolutePath , nuevoT)
                val aT = OutputStreamWriter( FileOutputStream( ficheroNT ) )
                aT.write( file_titulo )
                aT.flush()
                aT.close()

                // GUARDAR RECORDATORIO
                var nuevoR = "notaR" + (numN-1).toString() + ".txt"
                val ficheroNR = File(tarjetaSD?.absolutePath , nuevoR)
                val aR = OutputStreamWriter( FileOutputStream( ficheroNR ) )
                aR.write( file_recordatorio )
                aR.flush()
                aR.close()

                // GUARDAR COLOR
                var nuevoC = "notaC" + (numN-1).toString() + ".txt"
                val ficheroNC = File(tarjetaSD?.absolutePath , nuevoC)
                val aC = OutputStreamWriter( FileOutputStream( ficheroNC ) )
                aC.write( file_color )
                aC.flush()
                aC.close()
            }
            catch (error: Exception)
            {
                numN--
                continuar = false
            }
        }

        try
        {
            val tarjetaSD = getExternalFilesDir(null)
            var fileTitulo = "notaT" + numN.toString() + ".txt"
            var fileRecordatorio = "notaR" + numN.toString() + ".txt"
            var fileColor = "notaC" + numN.toString() + ".txt"

            val ficheroEliminar1 = File(tarjetaSD?.absolutePath , fileTitulo)
            val ficheroEliminar2 = File(tarjetaSD?.absolutePath , fileRecordatorio)
            val ficheroEliminar3 = File(tarjetaSD?.absolutePath , fileColor)

            ficheroEliminar1.delete()
            ficheroEliminar2.delete()
            ficheroEliminar3.delete()

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("NOTA ELIMINADA")
            dialog.setMessage("La nota se ha eliminado con exito")
            dialog.setPositiveButton("OK" , DialogInterface.OnClickListener { dialog , i ->
                dialog.dismiss()
                var irAPrincipal = Intent(this , pantalla_listaDeNotas::class.java)
                startActivity(irAPrincipal)
                finish()
            })
            dialog.show()
        }
        catch (error: Exception)
        {
            Toast.makeText(this,error.message,Toast.LENGTH_LONG)
        }
    }


    private fun eliminarEnMemoriaInterna( numero_nota: Int) {
        var continuar = true
        var numN = numero_nota
        while( continuar == true )
        {
            try
            {
                // OBTENER DATOS DE LA SIGUIENTE NOTA
                numN++

                // OBTENER EL TITULO
                var archivoTitulo = "notaT" + numN.toString() + ".txt"
                val archivoT = BufferedReader( InputStreamReader( openFileInput(archivoTitulo) ) )
                var file_titulo = ""
                var lineaT = archivoT.readLine()
                while( lineaT != null )
                {
                    file_titulo = file_titulo + lineaT + "\n"
                    lineaT = archivoT.readLine()
                }
                file_titulo = file_titulo.substring(0 , file_titulo.length-1)
                archivoT.close()

                // OBTENER EL RECORDATORIO
                var archivoRecordatorio = "notaR" + numN.toString() + ".txt"
                val archivoR = BufferedReader( InputStreamReader( openFileInput(archivoRecordatorio) ) )
                var file_recordatorio = ""
                var lineaR = archivoR.readLine()
                while( lineaR != null )
                {
                    file_recordatorio = file_recordatorio + lineaR + "\n"
                    lineaR = archivoR.readLine()
                }
                file_recordatorio = file_recordatorio.substring(0 , file_recordatorio.length-1)
                archivoR.close()

                // OBTENER EL COLOR
                var archivoColor = "notaC" + numN.toString() + ".txt"
                val archivoC = BufferedReader( InputStreamReader( openFileInput(archivoColor) ) )
                var file_color = ""
                var lineaC = archivoC.readLine()
                while( lineaC != null )
                {
                    file_color = file_color + lineaC + "\n"
                    lineaC = archivoC.readLine()
                }
                file_color = file_color.substring(0 , file_color.length-1)
                archivoC.close()

                // ESCRIBIR SOBRE LA NOTA ACTUAL
                // GUARDAR TITULO
                var nuevoT = "notaT" + (numN-1).toString() + ".txt"
                val aT = OutputStreamWriter( openFileOutput( nuevoT , MODE_PRIVATE ) )
                aT.write( file_titulo )
                aT.flush()
                aT.close()

                // GUARDAR RECORDATORIO
                var nuevoR = "notaR" + (numN-1).toString() + ".txt"
                val aR = OutputStreamWriter( openFileOutput( nuevoR , MODE_PRIVATE ) )
                aR.write( file_recordatorio )
                aR.flush()
                aR.close()

                // GUARDAR COLOR
                var nuevoC = "notaC" + (numN-1).toString() + ".txt"
                val aC = OutputStreamWriter( openFileOutput( nuevoC , MODE_PRIVATE ) )
                aC.write( file_color )
                aC.flush()
                aC.close()
            }
            catch (error: Exception)
            {
                numN--
                continuar = false
            }
        }

        try
        {

            var fileTitulo = "notaT" + numN.toString() + ".txt"
            var fileRecordatorio = "notaR" + numN.toString() + ".txt"
            var fileColor = "notaC" + numN.toString() + ".txt"
            deleteFile( fileTitulo )
            deleteFile( fileRecordatorio )
            deleteFile( fileColor )

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("NOTA ELIMINADA")
            dialog.setMessage("La nota se ha eliminado con exito")
            dialog.setPositiveButton("OK" , DialogInterface.OnClickListener { dialog , i ->
                dialog.dismiss()
                var irAPrincipal = Intent(this , pantalla_listaDeNotas::class.java)
                startActivity(irAPrincipal)
                finish()
            })
            dialog.show()
        }
        catch (error: Exception)
        {
            Toast.makeText(this,error.message,Toast.LENGTH_LONG)
        }
    }


    private fun guardarEnMemoriaExterna() : Boolean
    {
        if (colorNota == "")
        {
            Toast.makeText(this , "DEBES ELEGIR EL COLOR DE LA NOTA" , Toast.LENGTH_SHORT ).show()
            return false
        }
        var titulo = cajaTitulo.text.toString()
        var recordatorio = cajaRecordatorio.text.toString()
        if (titulo == "")
        {
            Toast.makeText(this , "INDRODUZCA UN TITULO" , Toast.LENGTH_SHORT ).show()
            return false
        }
        if (recordatorio == "")
        {
            Toast.makeText(this , "INTRODUZCA EL RECORDATORIO" , Toast.LENGTH_SHORT ).show()
            return false
        }
        var numN = 1
        var continuar = true
        while( continuar == true )
        {
            try
            {
                // OBTENER NOTA
                    val tarjetaSD = getExternalFilesDir(null)
                var archivoTitulo = "notaT" + numN.toString() + ".txt"
                val fichero = File(tarjetaSD?.absolutePath , archivoTitulo)
                val archivoT = BufferedReader( InputStreamReader( FileInputStream(fichero) ) )
                numN++
                archivoT.close()
            }
            catch (error: Exception)
            {
                continuar = false
            }
        }
        try
        {
            val tarjetaSD = getExternalFilesDir(null)
            // GUARDAR TITULO
            var nuevoArchivoTitulo = "notaT" + numN.toString() + ".txt"
            val ficheroT = File(tarjetaSD?.absolutePath , nuevoArchivoTitulo)
            val archivoT = OutputStreamWriter( FileOutputStream( ficheroT ) )
            archivoT.write( titulo )
            archivoT.flush()
            archivoT.close()

            // GUARDAR RECORDATORIO
            var nuevoArchivoRecordatorio = "notaR" + numN.toString() + ".txt"
            val ficheroR = File(tarjetaSD?.absolutePath , nuevoArchivoRecordatorio)
            val archivoR = OutputStreamWriter( FileOutputStream( ficheroR ) )
            archivoR.write( recordatorio )
            archivoR.flush()
            archivoR.close()

            // GUARDAR COLOR
            var nuevoArchivoColor = "notaC" + numN.toString() + ".txt"
            val ficheroC = File(tarjetaSD?.absolutePath , nuevoArchivoColor)
            val archivoC = OutputStreamWriter( FileOutputStream( ficheroC ) )
            archivoC.write( colorNota )
            archivoC.flush()
            archivoC.close()

            // LIMPIAR CAMPOS Y MANDAR MENSAJE DE EXITO
            cajaTitulo.setText( "" )
            cajaRecordatorio.setText( "" )
            img_telefono.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#003C5C17")))
            img_sd.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#003C5C17")))
            frameAzul.removeAllViews()
            frameVerde.removeAllViews()
            frameMorado.removeAllViews()
            frameRojo.removeAllViews()
            tipoMemoria = ""
            colorNota = ""
            Toast.makeText( this , "NOTA GUARDADA CON EXITO!!!" , Toast.LENGTH_SHORT ).show()
        }
        catch (error2: Exception)
        {
            // ERROR AL GUARDAR
            Toast.makeText( this , error2.message , Toast.LENGTH_LONG ).show()
        }
        return false
    }


    private fun guardarEnMemoriaInterna(): Boolean
    {
        if (colorNota == "")
        {
            Toast.makeText(this , "DEBES ELEGIR EL COLOR DE LA NOTA" , Toast.LENGTH_SHORT ).show()
            return false
        }
        var titulo = cajaTitulo.text.toString()
        var recordatorio = cajaRecordatorio.text.toString()
        if (titulo == "")
        {
            Toast.makeText(this , "INDRODUZCA UN TITULO" , Toast.LENGTH_SHORT ).show()
            return false
        }
        if (recordatorio == "")
        {
            Toast.makeText(this , "INTRODUZCA EL RECORDATORIO" , Toast.LENGTH_SHORT ).show()
            return false
        }
        var numN = 1
        var continuar = true
        while( continuar == true )
        {
            try
            {
                // OBTENER NOTA
                var archivoTitulo = "notaT" + numN.toString() + ".txt"
                val archivoT = BufferedReader( InputStreamReader( openFileInput(archivoTitulo) ) )
                numN++
                archivoT.close()
            }
            catch (error: Exception)
            {
                continuar = false
            }
        }
        try
        {
            // GUARDAR TITULO
            var nuevoArchivoTitulo = "notaT" + numN.toString() + ".txt"
            val archivoT = OutputStreamWriter( openFileOutput( nuevoArchivoTitulo , MODE_PRIVATE ) )
            archivoT.write( titulo )
            archivoT.flush()
            archivoT.close()

            // GUARDAR RECORDATORIO
            var nuevoArchivoRecordatorio = "notaR" + numN.toString() + ".txt"
            val archivoR = OutputStreamWriter( openFileOutput( nuevoArchivoRecordatorio , MODE_PRIVATE ) )
            archivoR.write( recordatorio )
            archivoR.flush()
            archivoR.close()

            // GUARDAR COLOR
            var nuevoArchivoColor = "notaC" + numN.toString() + ".txt"
            val archivoC = OutputStreamWriter( openFileOutput( nuevoArchivoColor , MODE_PRIVATE ) )
            archivoC.write( colorNota )
            archivoC.flush()
            archivoC.close()

            // LIMPIAR CAMPOS Y MANDAR MENSAJE DE EXITO
            cajaTitulo.setText( "" )
            cajaRecordatorio.setText( "" )
            img_telefono.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#003C5C17")))
            img_sd.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#003C5C17")))
            frameAzul.removeAllViews()
            frameVerde.removeAllViews()
            frameMorado.removeAllViews()
            frameRojo.removeAllViews()
            tipoMemoria = ""
            colorNota = ""
            Toast.makeText( this , "NOTA GUARDADA CON EXITO!!!" , Toast.LENGTH_SHORT ).show()
        }
        catch (error2: Exception)
        {
            // ERROR AL GUARDAR
            Toast.makeText( this , error2.message , Toast.LENGTH_LONG ).show()
        }
        return false
    }
}