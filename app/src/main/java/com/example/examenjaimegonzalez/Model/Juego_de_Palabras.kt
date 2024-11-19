package com.example.examenjaimegonzalez.Model

class Juego_de_Palabras {
    // es un arraylist por si en el futuro quiero poder añadir mas valores a la lista
    private var listaPalabras: List<String>
        get() {
            return field
        }
        set(value) {
            field = value
        }

    // es una lista porque son inmutables
    private val pistas: List<String> = listOf(
        "Faltan caracteres",
        "Cambio de vocal",
        "Posición caracter",
        "Cambio de consonante"
    ) // la ultima puede cambiar, es la primera que se me ocurre
        get() {
            return field
        }

    private var puntos: Int = 2
        get() {
            return field
        }
        set(value) {
            field = value
        }

    constructor(listaPalabras: List<String>) {
        this.listaPalabras = listaPalabras
    }

    fun obtener_Palabra(): String {
        return this.listaPalabras.random()
    }

    fun obtenerPista(num: Int): String {
        return this.pistas[num]
    }
}