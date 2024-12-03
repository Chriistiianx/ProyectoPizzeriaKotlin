package com.aplicacionpizzeria.modelo

enum class Size {
    SMALL,
    MEDIUM,
    LARGE;

    fun getNombre() = when(this){
        SMALL-> "Pequeño"
        MEDIUM-> "Mediano"
        LARGE-> "Grande"
    }
}