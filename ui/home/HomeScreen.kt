package com.example.pizzazo.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aplicacionpizzeria.modelo.Size
import com.example.pizzazo.R
import com.example.pizzazo.data.ProductoDTO
import com.example.pizzazo.data.TipoProducto
import com.example.pizzazo.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDisplay(viewModel: HomeViewModel, navController: NavHostController){
    val listaProductos : List<ProductoDTO> by viewModel.productos.observeAsState(listOf())
    val pizzas = listaProductos.filter { producto -> producto.tipo== TipoProducto.PIZZA }
    val bebidas = listaProductos.filter { producto -> producto.tipo== TipoProducto.BEBIDA }
    val pastas = listaProductos.filter { producto -> producto.tipo== TipoProducto.PASTA }

    val listaFotos:List<Int> = listOf(R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza4, R.drawable.pizza2, R.drawable.gahpayo, R.drawable.cola, R.drawable.cola, R.drawable.nestea, R.drawable.pasta1, R.drawable.pasta2, R.drawable.proscciuto, R.drawable.plato)

    val carrito by viewModel.numCarrito.observeAsState()
    var mostrarDialog by remember { mutableStateOf(false) } // Controla el diálogo

    LazyColumn (
        modifier = Modifier.fillMaxSize().background(com.example.compose.colorClaroBackground)
    ){
        item {
            TopAppBar(
                title = {
                    Image(painter = painterResource(R.drawable.pizzazo), contentDescription = "",
                    Modifier
                        .padding(10.dp)
                        .size(75.dp)) },
                navigationIcon = {
                    IconButton(onClick = {
                        mostrarDialog = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = com.example.compose.colorLabels
                        )
                    }
                },
                actions = { BadgedBox(badge = { Badge { Text(text = carrito.toString())} },  modifier = Modifier.offset(x = -25.dp)) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito", tint = com.example.compose.colorLabels)
                    }

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = com.example.compose.colorTopApp)
            )
            Text(text = "Pizzas", modifier = Modifier.fillMaxSize(),fontWeight = FontWeight.Bold, color = com.example.compose.colorLabels, fontSize = 25.sp,textAlign = TextAlign.Center)
        }
        items(pizzas){pizzas-> ProductoCard(pizzas, listaFotos.get(pizzas.id), onAddLineaPedido = { cantidad, producto, size -> viewModel.addLineaPedido(cantidad, pizzas, Size.SMALL) })}
        item {
            Text(text = "Bebidas", modifier = Modifier.fillMaxSize(),fontWeight = FontWeight.Bold, color = com.example.compose.colorLabels, fontSize = 25.sp,textAlign = TextAlign.Center)
        }
        items(bebidas){bebidas-> ProductoCard(bebidas, listaFotos.get(bebidas.id), onAddLineaPedido = { cantidad, producto, size -> viewModel.addLineaPedido(cantidad, bebidas, Size.SMALL) })}
        item {
            Text(text = "Pastas", modifier = Modifier.fillMaxSize(),fontWeight = FontWeight.Bold, color = com.example.compose.colorLabels, fontSize = 25.sp, textAlign = TextAlign.Center)
        }
        items(pastas){pastas-> ProductoCard(pastas, listaFotos.get(pastas.id), onAddLineaPedido = { cantidad, producto, size -> viewModel.addLineaPedido(cantidad, pastas, Size.SMALL) })}
    }

    if (mostrarDialog) {
        AlertDialog(
            onDismissRequest = { mostrarDialog = false },
            title = { Text(text = "Cierre de sesión") },
            text = { Text("¿Estás seguro de que quieres cerrar sesión?") },
            confirmButton = {
                TextButton(onClick = {
                    mostrarDialog = false
                    navController.navigate(Screen.Login.route)
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}

@Composable
fun ProductoCard(producto: ProductoDTO, foto: Int, onAddLineaPedido: (cantidad:Int, producto: ProductoDTO, size: Size?) -> Unit){
    var contador by rememberSaveable { mutableStateOf(1) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var size: Size? by rememberSaveable { mutableStateOf(null) }
    var context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = com.example.compose.primaryContainerLightMediumContrast,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Row {
            Box(contentAlignment = Alignment.BottomCenter) {
                Image(
                    painter = painterResource(foto),
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp),
                    alignment = Alignment.TopStart,
                    contentDescription = "",
                )
                Text(
                    text = producto.precio.toString() + "€",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Column {
                Text(
                    text = producto.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = producto.listaIngredientes.joinToString(prefix = "- ", separator = "\n- ") { it.nombre },
                    modifier = Modifier.fillMaxSize(),
                    fontSize = 15.sp
                )
            }
        }
        Row (
            Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ){
            TextButton(onClick = {if (contador>1)contador--}, Modifier.padding(horizontal =20.dp)) {
                Text(text = "-", fontSize = 20.sp, color = Color.White)
            }
            Text(contador.toString(), fontSize = 20.sp)
            TextButton(onClick = {contador++}, Modifier.padding(horizontal = 20.dp)) {
                Text(text = "+", fontSize = 20.sp, color = Color.White)
            }
            Column {
                Row {
                    if (producto.tipo != TipoProducto.PASTA) {
                        TextButton(onClick = { expanded = !expanded }) {
                            Text(text = size?.getNombre() ?: "Tamaño", color = Color.White)
                        }
                    }
                    if (size != null){
                        TextButton(onClick = {
                            onAddLineaPedido(contador, producto, size)
                            if (contador>1){Toast.makeText(context, "Se han añadido x$contador de '" + producto.nombre + "' al carrito.", Toast.LENGTH_LONG).show()}
                            else Toast.makeText(context, "Se ha añadido x$contador de '" + producto.nombre + "' al carrito.", Toast.LENGTH_LONG).show()
                        }) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito",)
                        }
                    }

                    DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                        DropdownMenuItem( onClick = {
                            size = Size.SMALL
                            expanded = false}, text = {Text(Size.SMALL.getNombre())})
                        DropdownMenuItem( onClick = {
                            size = Size.MEDIUM
                            expanded = false}, text = {Text(Size.MEDIUM.getNombre())})
                        DropdownMenuItem( onClick = {
                            size = Size.LARGE
                            expanded = false}, text = {Text(Size.LARGE.getNombre())})
                    }
                }
            }
        }
    }
}