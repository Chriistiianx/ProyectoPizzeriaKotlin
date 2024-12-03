package com.example.pizzazo.data

import com.aplicacionpizzeria.modelo.IngredienteDTO
import com.aplicacionpizzeria.modelo.Size

class ProductoDTO (
    val id: Int,
    val nombre:String,
    val precio:Double,
    var tipo: TipoProducto,
    var size:Size? = null,
    val listaIngredientes:List<IngredienteDTO>,
){
    override fun toString(): String {
        return nombre
    }
}