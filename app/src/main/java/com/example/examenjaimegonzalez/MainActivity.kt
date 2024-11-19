package com.example.examenjaimegonzalez

import android.os.Bundle
import android.text.method.KeyListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.examenjaimegonzalez.Extension.transformar
import com.example.examenjaimegonzalez.Model.Juego_de_Palabras
import com.example.examenjaimegonzalez.Model.Timer
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvPalabraModificada: TextView
    private lateinit var tvPista: TextView
    private lateinit var etPalabraIntroducida: EditText
    private lateinit var btnCompruebaPalabra: Button
    private lateinit var tvtimer: TextView
    private lateinit var tvPuntuacion: TextView
    private lateinit var ivImagen: ImageView
    private lateinit var btnComenzarJuego: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tvPalabraModificada = findViewById(R.id.tvPalabraModificada)
        tvPista = findViewById(R.id.tvPista)
        etPalabraIntroducida = findViewById(R.id.etPalabraAAdivinar)
        var tag = etPalabraIntroducida.keyListener
        tvtimer = findViewById(R.id.tvTimer)
        btnCompruebaPalabra = findViewById(R.id.btnComprobarPalabra)
        tvPuntuacion = findViewById(R.id.tvPuntuacion)
        ivImagen = findViewById(R.id.ivImagen)
        btnComenzarJuego = findViewById(R.id.btnComenzarJuego)
        iniciarApp()

        btnComenzarJuego.setOnClickListener { comenzarJuego(tag) }
    }

    fun iniciarApp() {
        val valorInicialTimer = "3:00"
        val puntuacionInicial = 2
        etPalabraIntroducida.keyListener = null
        tvtimer.text = valorInicialTimer
        btnCompruebaPalabra.isEnabled = false
        tvPuntuacion.text = puntuacionInicial.toString()
        //ivImagen
        btnComenzarJuego.isEnabled = true
    }

    fun comenzarJuego(tag: KeyListener) {
        etPalabraIntroducida.keyListener = tag
        btnCompruebaPalabra.isEnabled = true
        btnComenzarJuego.isEnabled = false
        //ivImagen.drawable

        val listaPalabras: List<String> = listOf(
            "patata",
            "jaime",
            "examen",
            "kotlin",
            "busca",
            "encuentra",
            "juego",
            "palabra",
            "bote",
            "dia",
            "lista"
        )

        val juego = Juego_de_Palabras(listaPalabras)
        var palabraAleatoria = juego.obtener_Palabra()
        var pista = juego.obtenerPista(Random.nextInt(0, 3))
        var palabraPreCambio = palabraAleatoria
        /*
         "Faltan caracteres",
        "Cambio de vocal",
        "Posición caracter",
        "Cambio de consonante"
         */
        when (pista) {
            "Faltan caracteres" -> {
                palabraAleatoria.transformar( // no transforma
                    false
                ) { caracter, n ->
                    if (caracter == 'a') {
                        '_'
                    } else {
                        caracter
                    }
                }
                tvPista.text = "Faltan caracteres"
            }

            "Cambio de vocal" -> {
                palabraAleatoria.transformar( // no transforma
                    false
                ) { caracter, n ->
                    if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u') {
                        'c'
                    } else {
                        caracter
                    }
                }
                tvPista.text = "Cambio de vocal"

            }

            "Posición caracter" -> {
                palabraAleatoria.transformar( // no transforma
                    false
                ) { caracter, n ->
                    if (caracter == 'a' || caracter == 'b' || caracter == 'c' || caracter == 'd' || caracter == 'f') {// se que me faltan el resto
                        caracter.lowercaseChar().code.toChar()
                    } else {
                        caracter
                    }
                }
                tvPista.text = "Posición caracter"

            }

            "Cambio de consonante" -> {
                palabraAleatoria.transformar( // no transforma
                    false
                ) { caracter, n ->
                    if (caracter == 'b' || caracter == 'c' || caracter == 'd' || caracter == 'f' || caracter == 'g') {// se que me faltan el resto
                        'a'
                    } else {
                        caracter
                    }
                }
                tvPista.text = "Cambio de consonante"

            }
        }
        tvPalabraModificada.text = palabraAleatoria
        btnCompruebaPalabra.setOnClickListener {
            var palabraEscrita = etPalabraIntroducida.text
            if (palabraEscrita.equals(palabraPreCambio)) { // no funciona
                var sumaPuntuacion = Integer.parseInt(tvPuntuacion.text.toString())
                sumaPuntuacion++
                tvPuntuacion.text = sumaPuntuacion.toString()
            } else {
                var restaPuntuacion = Integer.parseInt(tvPuntuacion.text.toString())
                restaPuntuacion--
                tvPuntuacion.text = restaPuntuacion.toString()
            }
        }
        var timer = Timer(180000, 1)
        // timer.start() // peta al empezar
        // tvtimer.text = timer.onTick(180000).toString()

    }
}