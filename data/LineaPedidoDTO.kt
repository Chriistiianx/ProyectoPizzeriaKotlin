package com.aplicacionpizzeria.modelo

import com.example.pizzazo.data.ProductoDTO

data class LineaPedidoDTO(
    val id: Int,
    val cantidad: Int,
    val producto: ProductoDTO
)
