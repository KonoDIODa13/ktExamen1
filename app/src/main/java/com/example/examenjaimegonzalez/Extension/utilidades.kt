package com.example.examenjaimegonzalez.Extension


fun String.transformar(
    descolocada: Boolean,
    transformacion: (caracter: Char, n: Int) -> Char
): String {

    /* var cambiar_caracter = { car: Char, pos: Int ->
         if ((pos % 2 != 0) && (car == 'a')) 'z'
         else car
     }*/
    if (descolocada) {
        this.toList().reversed()
    }
// puede ser que esto no este bien y no me transforme las letras
    return this.forEachIndexed { index, char -> transformacion(char, index) }.toString()
}