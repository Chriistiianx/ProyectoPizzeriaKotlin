package com.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ModeFanOff
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aplicacionpizzeria.modelo.ClienteDTO
import com.example.pizzazo.R
import com.example.pizzazo.data.ErrorMessage
import com.example.pizzazo.navigation.Screen

@Composable
fun RegistroDisplay(viewModel: RegistroViewModel, navController: NavHostController){
    val cliente : ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO())
    val registro : Boolean by viewModel.botonRegistro.observeAsState(false)
    val error : ErrorMessage by viewModel.errorMsg.observeAsState(ErrorMessage("", "", ""))

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            Image(
                modifier = Modifier.fillMaxWidth().padding(50.dp),
                painter = painterResource(R.drawable.pizzazo),
                contentDescription = ""
            )
            Text(
                text = "Crea tu cuenta",
                fontSize = 40.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Fields("Nombre", cliente.nombre, error.nombre, {viewModel.onClienteChange(cliente.copy(nombre = it))})
            Fields("DNI", cliente.dni, valueChange = {viewModel.onClienteChange(cliente.copy(dni = it))})
            Fields("Dirección", cliente.direccion, valueChange = {viewModel.onClienteChange(cliente.copy(direccion = it))})
            Fields("Teléfono", cliente.telefono, valueChange = {viewModel.onClienteChange(cliente.copy(telefono = it))}, keyboardType = KeyboardType.Number)
            Fields("Email", cliente.email, error.email, {viewModel.onClienteChange(cliente.copy(email = it))}, KeyboardType.Email)
            Fields("Contraseña", cliente.password, error.password, {viewModel.onClienteChange(cliente.copy(password = it))}, KeyboardType.Password, Icons.Filled.VisibilityOff)
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    viewModel.onRegisterCliente()
                }, Modifier.fillMaxWidth(), enabled = registro) {
                    Text("Registrarse")
                }
            }
            Text(
                text = "Ya tienes una cuenta? Inicia sesión.",
                Modifier.fillMaxSize().clickable { navController.navigate(Screen.Login.route)

                }
            )
        }
    }
}

@Composable
fun Fields(string:String, value:String, errorMsg:String = "", valueChange:(String)->Unit, keyboardType: KeyboardType = KeyboardType.Text, icono: ImageVector = Icons.Filled.ModeFanOff){
    var icon2 by remember { mutableStateOf(Icons.Filled.VisibilityOff) }
    var isVisual by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        value = value,
        onValueChange = valueChange,
        label = { Text(string) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation =  if (isVisual || keyboardType != KeyboardType.Password) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            if (icono != Icons.Filled.ModeFanOff){
                IconButton(onClick = {
                    if (icon2 == Icons.Filled.VisibilityOff) {
                        icon2 = Icons.Filled.Visibility
                        isVisual = true
                    } else {
                        icon2 = Icons.Filled.VisibilityOff
                        isVisual = false
                    }
                }) {
                    Icon(
                        imageVector = icon2,
                        contentDescription = ""
                    )
                }

            }
        }
    )
    if (errorMsg.isNotBlank() && value.isNotBlank()) {
        Text(color = MaterialTheme.colorScheme.error,
            text = errorMsg)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //RegistroDisplay(RegistroViewModel(), NavHostController)
}