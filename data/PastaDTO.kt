package com.aplicacionpizzeria.modelo

data class PastaDTO(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val ingredientes: List<IngredienteDTO>
)
