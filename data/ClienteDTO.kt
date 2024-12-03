package com.aplicacionpizzeria.modelo

data class ClienteDTO(
    val id:Int? = null,
    val dni:String = "",
    val nombre:String = "",
    val direccion:String = "",
    val telefono:String = "",
    val email:String = "",
    val password:String = "",
    val pedidos: List<PedidoDTO> = emptyList()
)
