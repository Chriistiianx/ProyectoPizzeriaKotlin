package com.aplicacionpizzeria.modelo

import java.util.Date

data class PedidoDTO(
    val id: Int,
    val fecha: Date,
    val estado: EstadoPedido,
    var precioTotal: Double,
    var lineaPedidoDTO: MutableList<LineaPedidoDTO>
)
