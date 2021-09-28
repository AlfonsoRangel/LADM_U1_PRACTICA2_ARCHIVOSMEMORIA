package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoria

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.*
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.widget.*
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import java.io.*

class pantalla_listaDeNotas : AppCompatActivity() {

    lateinit var layoutPantalla: LinearLayout;
    lateinit var boton_agregarNota: ImageView;
    lateinit var boton_info: ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_lista_de_notas)

        // ASOCIAR ELEMENTOS
        layoutPantalla = findViewById(R.id.layoutPrincipal)
        boton_agregarNota = findViewById(R.id.boton_agregarNota)
        boton_info = findViewById(R.id.boton_info)

        // EVENTO CLICK EN BOTON INFO
        boton_info.setOnClickListener {
            Toast.makeText(this , "Dar clic en la nota para abrirla" , Toast.LENGTH_SHORT).show()
        }

        // EVENTO CLICK EN BOTON AGREGAR NOTA
        boton_agregarNota.setOnClickListener {
            var irAFormulario = Intent(this , formulario_nota::class.java)
            irAFormulario.putExtra("numNota" , -1)
            startActivity(irAFormulario)
            finish()
        }

        mostrarNotasInternas()
        mostrarNotasExternas()
    }

    private fun eliminar() {
        var items = fileList().size
        while( items > 0 )
        {
            try
            {
                // OBTENER NOTA
                var file = fileList()[0]
                deleteFile( file )
                items = fileList().size
            }
            catch (error: Exception)
            {
                Toast.makeText(this,error.message,Toast.LENGTH_LONG)
                items = 0
            }
        } // FIN WHILE
    }


    private fun mostrarNotasInternas()
    {
        /* Toast.makeText( this , fileList().size.toString() , Toast.LENGTH_SHORT ).show()
        try {
            var archivos = fileList()
            for (i in 0..fileList().size-1) {
                Toast.makeText(this, archivos[i], Toast.LENGTH_SHORT).show()
            }
        }catch(error: Exception){ Toast.makeText( this , error.message , Toast.LENGTH_LONG ).show() } */
        var numNOTA = 1
        var continuar = true
        while( continuar == true )
        {
            try
            {
                // OBTENER EL TITULO
                var archivoTitulo = "notaT" + numNOTA.toString() + ".txt"
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
                var archivoRecordatorio = "notaR" + numNOTA.toString() + ".txt"
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
                var archivoColor = "notaC" + numNOTA.toString() + ".txt"
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

                agregarNota(  file_titulo , file_recordatorio , numNOTA , file_color , "INTERNA")
                numNOTA++
            }
            catch (error: Exception)
            {
                continuar = false
            }
        } // FIN WHILE
        Toast.makeText( this , "NOTAS INTERNAS CARGADAS CON EXITO" , Toast.LENGTH_SHORT ).show()
    }


    private fun mostrarNotasExternas()
    {
        /* Toast.makeText( this , fileList().size.toString() , Toast.LENGTH_SHORT ).show()
        try {
            var archivos = fileList()
            for (i in 0..fileList().size-1) {
                Toast.makeText(this, archivos[i], Toast.LENGTH_SHORT).show()
            }
        }catch(error: Exception){ Toast.makeText( this , error.message , Toast.LENGTH_LONG ).show() } */
        var numNOTA = 1
        var continuar = true
        while( continuar == true )
        {
            try
            {
                val tarjetaSD = getExternalFilesDir(null)
                // OBTENER EL TITULO
                var archivoTitulo = "notaT" + numNOTA.toString() + ".txt"
                val ficheroT = File(tarjetaSD?.absolutePath , archivoTitulo)
                val archivoT = BufferedReader( InputStreamReader( FileInputStream(ficheroT) ) )
                var file_titulo = ""
                var linea = archivoT.readLine()
                while( linea != null )
                {
                    file_titulo = file_titulo + linea + "\n"
                    linea = archivoT.readLine()
                }
                file_titulo = file_titulo.substring(0 , file_titulo.length-1)
                archivoT.close()

                // OBTENER EL RECORDATORIO
                var archivoRecordatorio = "notaR" + numNOTA.toString() + ".txt"
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
                var archivoColor = "notaC" + numNOTA.toString() + ".txt"
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

                //Toast.makeText(this , file_color , Toast.LENGTH_LONG).show()
                agregarNota(  file_titulo , file_recordatorio , numNOTA , file_color , "EXTERNA")
                numNOTA++
            }
            catch (error: Exception)
            {
                continuar = false
            }
        } // FIN WHILE
        Toast.makeText( this , "NOTAS EXTERNAS CARGADAS CON EXITO" , Toast.LENGTH_SHORT ).show()
    }



    @SuppressLint("ResourceAsColor")
    fun agregarNota( titulo: String , recordatorio: String , numNota: Int ,
                     color: String , tipoMemoria: String)
    {
        try {
            //==================================================================================
            // CONTENEDOR NOTA
            // ==================================================================================
            val layout_contenedor_nota = LinearLayout( ContextThemeWrapper( this , R.style.contenedor_nota))
            layoutPantalla.addView(layout_contenedor_nota)

            val params1 = layout_contenedor_nota.layoutParams as LinearLayout.LayoutParams
            params1.bottomMargin = 15
            params1.width = LinearLayout.LayoutParams.MATCH_PARENT
            params1.height = LinearLayout.LayoutParams.WRAP_CONTENT
            layout_contenedor_nota.orientation = LinearLayout.HORIZONTAL
            params1.leftMargin = 15
            params1.rightMargin = 15
            layout_contenedor_nota.layoutParams = params1

            // COLOR DE FONDO
            when (color) {
                "ROJO" -> {
                    layout_contenedor_nota.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor( "#EFBB7C79")))
                }
                "AZUL" -> {
                    layout_contenedor_nota.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#6E8D9A") ))
                }
                "VERDE" -> {
                    layout_contenedor_nota.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#659868") ))
                }
                "MORADO" -> {
                    layout_contenedor_nota.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#F8A385A7") ))
                }
            }


            //==================================================================================
            // CONTENEDOR LATERAL
            // ==================================================================================
            val layout_lateral = LinearLayout(ContextThemeWrapper(this, R.style.contenedor_nota_lateral))
            layout_contenedor_nota.addView(layout_lateral)
            layout_lateral.setPadding(0 , 5 , 0 , 5)

            val params2 = layout_lateral.layoutParams as LinearLayout.LayoutParams
            params2.width = LinearLayout.LayoutParams.WRAP_CONTENT
            params2.height = LinearLayout.LayoutParams.MATCH_PARENT
            layout_lateral.orientation = LinearLayout.VERTICAL
            layout_lateral.layoutParams = params2


            //==================================================================================
            // TXT NUMERO DE NOTA
            // ==================================================================================
            val txt_num_nota = TextView(ContextThemeWrapper(this, R.style.txt_numero_nota))
            layout_lateral.addView(txt_num_nota)
            txt_num_nota.setText("${numNota}")
            txt_num_nota.setTypeface(Typeface.DEFAULT_BOLD)
            txt_num_nota.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            txt_num_nota.setTextSize(TypedValue.COMPLEX_UNIT_SP , 20f)

            val params3 = txt_num_nota.layoutParams as LinearLayout.LayoutParams
            params3.width = LinearLayout.LayoutParams.MATCH_PARENT
            params3.height = LinearLayout.LayoutParams.WRAP_CONTENT
            params3.setMargins(5)
            txt_num_nota.layoutParams = params3

            // COLOR DEL NUMERO DE NOTA
            when (color) {
                "ROJO" -> {
                    txt_num_nota.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor( "#591915")))
                }
                "AZUL" -> {
                    txt_num_nota.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#0B3B50") ))
                }
                "VERDE" -> {
                    txt_num_nota.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#0F3310") ))
                }
                "MORADO" -> {
                    txt_num_nota.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#411448") ))
                }
            }

            //==================================================================================
            // IMAGEN DEL TIPO DE MEMORIA
            // ==================================================================================
            val img_tipo_memoria = ImageView(ContextThemeWrapper(this, R.style.img_tipo_memoria))
            layout_lateral.addView(img_tipo_memoria)

            val params4 = img_tipo_memoria.layoutParams as LinearLayout.LayoutParams
            params4.width = 75
            params4.height = 75
            params4.weight = 1f
            img_tipo_memoria.layoutParams = params4

            // ASIGNAR IMAGEN
            when (tipoMemoria) {
                "INTERNA" -> {
                    img_tipo_memoria.setImageResource(R.drawable.icono_telefono)
                }
                "EXTERNA" -> {
                    img_tipo_memoria.setImageResource(R.drawable.icono_sd)
                }
            }

            // COLOR DE LA IMAGEN
            when (color) {
                "ROJO" -> {
                    img_tipo_memoria.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor( "#591915")))
                }
                "AZUL" -> {
                    img_tipo_memoria.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#0B3B50") ))
                }
                "VERDE" -> {
                    img_tipo_memoria.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#0F3310") ))
                }
                "MORADO" -> {
                    img_tipo_memoria.setBackgroundTintList(
                        ColorStateList.valueOf( Color.parseColor("#411448") ))
                }
            }

            //==================================================================================
            // CONTENEDOR CONTENIDO
            // ==================================================================================
            val layout_contenido = LinearLayout(ContextThemeWrapper(this, R.style.contenido_nota) )
            layout_contenedor_nota.addView(layout_contenido)
            layout_contenido.orientation = LinearLayout.VERTICAL

            val params5 = layout_contenido.layoutParams as LinearLayout.LayoutParams
            params5.width = LinearLayout.LayoutParams.MATCH_PARENT
            params5.height = LinearLayout.LayoutParams.WRAP_CONTENT
            params5.weight = 1f
            params5.rightMargin = 12
            params5.leftMargin = 10
            params5.topMargin = 5
            params5.bottomMargin = 8
            layout_contenido.layoutParams = params5

            // EVENTO CLICK PARA ABRIR LA NOTA
            layout_contenido.setOnClickListener {
                var irAFormulario = Intent(this , formulario_nota::class.java)
                irAFormulario.putExtra("numNota" , numNota)
                irAFormulario.putExtra("titulo" , titulo)
                irAFormulario.putExtra("recordatorio" , recordatorio)
                irAFormulario.putExtra("color" , color)
                irAFormulario.putExtra("tipoMemoria" , tipoMemoria)
                startActivity(irAFormulario)
                finish()
            }


            //==================================================================================
            // TXT TITULO
            // ==================================================================================
            val txt_titulo = TextView(ContextThemeWrapper(this, R.style.titulo_nota)  )
            layout_contenido.addView(txt_titulo)
            txt_titulo.setText(titulo)
            txt_titulo.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            txt_titulo.setTextSize(TypedValue.COMPLEX_UNIT_SP , 23f)
            txt_titulo.setTypeface(Typeface.DEFAULT_BOLD)
            txt_titulo.ellipsize = TextUtils.TruncateAt.END
            txt_titulo.maxLines = 2
            txt_titulo.setPadding(0,0,0,6)

            val params6 = txt_titulo.layoutParams as LinearLayout.LayoutParams
            params6.width = LinearLayout.LayoutParams.MATCH_PARENT
            params6.height = LinearLayout.LayoutParams.WRAP_CONTENT
            txt_titulo.layoutParams = params6

            //==================================================================================
            // TXT RECORDATORIO
            // ==================================================================================
            val txt_recordatorio = TextView(ContextThemeWrapper(this, R.style.txt_nota))
            layout_contenido.addView(txt_recordatorio)
            txt_recordatorio.setText(recordatorio)
            txt_recordatorio.textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
            txt_recordatorio.setTextSize(TypedValue.COMPLEX_UNIT_SP , 17f)
            txt_recordatorio.ellipsize = TextUtils.TruncateAt.END
            txt_recordatorio.maxLines = 2

            val params7 = txt_recordatorio.layoutParams as LinearLayout.LayoutParams
            params7.width = LinearLayout.LayoutParams.MATCH_PARENT
            params7.height = LinearLayout.LayoutParams.WRAP_CONTENT
            txt_recordatorio.layoutParams = params7
        }
        catch(error: Exception)
        { Toast.makeText(this, error.message , Toast.LENGTH_LONG).show() }
    }

/*
<!-- NOTA ROJA
* */
}