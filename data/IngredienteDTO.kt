package com.aplicacionpizzeria.modelo

data class IngredienteDTO(
    val id:Int,
    val nombre:String,
    val alergenos:List<String>
){
    override fun toString(): String {
        return nombre
    }
}
