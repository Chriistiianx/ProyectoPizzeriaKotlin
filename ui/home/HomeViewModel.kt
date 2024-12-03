package com.example.pizzazo.ui.home

import android.util.Log
import androidx.compose.material3.Text
import androidx.lifecycle.MutableLiveData
import com.aplicacionpizzeria.modelo.EstadoPedido
import com.aplicacionpizzeria.modelo.IngredienteDTO
import com.aplicacionpizzeria.modelo.LineaPedidoDTO
import com.aplicacionpizzeria.modelo.PedidoDTO
import com.aplicacionpizzeria.modelo.Size
import com.example.pizzazo.data.LoginDTO
import com.example.pizzazo.data.ProductoDTO
import com.example.pizzazo.data.TipoProducto
import java.util.Date

class HomeViewModel {
    val productos = MutableLiveData(emptyList<ProductoDTO>())

    val numCarrito = MutableLiveData(0)
    val pedidoActual = MutableLiveData<PedidoDTO>()

    init {
        cargarProducto()
    }

    fun addLineaPedido(cantidad:Int, producto: ProductoDTO, size: Size? ){
        if (pedidoActual.value == null){
            pedidoActual.value = PedidoDTO(1, Date(), EstadoPedido.PENDIENTE, 0.0, mutableListOf())
        }

        producto.size = size
        var lineaPedido = LineaPedidoDTO(1, cantidad, producto)

        numCarrito.value = numCarrito.value?.plus(cantidad)

        pedidoActual.value?.lineaPedidoDTO?.add(lineaPedido)
        pedidoActual.value?.precioTotal = producto.precio * lineaPedido.cantidad

        Log.d("Pedido: ", pedidoActual.value.toString())
        Log.d("Línea de Pedido creada: ", lineaPedido.toString())
    }

    fun cargarProducto(){
        val listaProductos : List<ProductoDTO> = listOf(
            // Generamos las pizzas.
            ProductoDTO(0, "Cuatro Quesos", 12.00, TipoProducto.PIZZA, null, listOf(
                IngredienteDTO(1, "Mozarella", listOf()), IngredienteDTO(1, "Gorgonzola", listOf()),
                IngredienteDTO(1, "Parmesano", listOf()), IngredienteDTO(1, "Ricotta", listOf())
            )),
            ProductoDTO(1, "Barbacoa", 9.00, TipoProducto.PIZZA, null, listOf(
                IngredienteDTO(1, "Tomate", listOf()), IngredienteDTO(1, "Barbacoa", listOf())
            )),
            ProductoDTO(2, "Napolitana", 8.00, TipoProducto.PIZZA, null, listOf(
                IngredienteDTO(1, "Mozzarella", listOf()), IngredienteDTO(1, "Tomate", listOf()) , IngredienteDTO(1, "Aceite de Oliva", listOf()), IngredienteDTO(1, "Albahaca", listOf())
            )),
            ProductoDTO(3, "Barbacoa Especial", 8.00, TipoProducto.PIZZA, null, listOf(
                IngredienteDTO(1, "Tomate", listOf()), IngredienteDTO(1, "Tomate", listOf()) , IngredienteDTO(1, "Extra de barbacoa", listOf()), IngredienteDTO(1, "Albahaca", listOf())
            )),

            // Generamos las bebidas.
            ProductoDTO(4, "Gazpacho", 2.00, TipoProducto.BEBIDA, null, listOf()),
            ProductoDTO(5, "Coca Cola", 1.50, TipoProducto.BEBIDA, null, listOf()),
            ProductoDTO(6, "Coca Cola Zero", 2.00, TipoProducto.BEBIDA, null, listOf()),
            ProductoDTO(7, "Nestea", 2.00, TipoProducto.BEBIDA, null, listOf()),

            //Generamos las pastas.
            ProductoDTO(8, "Pasta Alfredo", 10.00, TipoProducto.PASTA, null, listOf(
                IngredienteDTO(1, "Pasta Fetuccini", listOf()), IngredienteDTO(1, "Perejil", listOf()),
                IngredienteDTO(1, "Sal", listOf()), IngredienteDTO(1, "Pimienta", listOf()),
            )),
            ProductoDTO(9, "Macarrones con Salchichas", 9.00, TipoProducto.PASTA, null, listOf(
                IngredienteDTO(1, "Pasta", listOf()), IngredienteDTO(1, "Perejil", listOf()),
                IngredienteDTO(1, "Salchichas", listOf()), IngredienteDTO(1, "Queso", listOf())
            )),
            ProductoDTO(10, "Proscciuto", 15.00, TipoProducto.PASTA, null, listOf(
                IngredienteDTO(1, "Pasta", listOf()), IngredienteDTO(1, "Agua", listOf()),
                IngredienteDTO(1, "Sal", listOf()), IngredienteDTO(1, "Mozarrella", listOf())
            )),
            ProductoDTO(11, "Pasta Nata", 15.00, TipoProducto.PASTA, null, listOf(
                IngredienteDTO(1, "Pasta", listOf()), IngredienteDTO(1, "Agua", listOf()),
                IngredienteDTO(1, "Sal", listOf()), IngredienteDTO(1, "Mozarrella", listOf())
            ))
        )

        productos.value = listaProductos
    }
}