package com.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pizzazo.R
import com.example.pizzazo.data.ErrorMessage
import com.example.pizzazo.data.LoginDTO
import com.example.pizzazo.navigation.Screen
import com.ui.registro.Fields

@Composable
fun LoginDisplay(viewModel: LoginViewModel, navController: NavHostController){
    val cliente : LoginDTO by viewModel.cliente.observeAsState(LoginDTO("", ""))
    val error = ""
    val activo:Boolean by viewModel.isActivo.observeAsState(false)

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
                text = "Inicio de sesión",
                fontSize = 40.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Fields("Email", cliente.email, error, {viewModel.onClienteLoginChange(cliente.copy(email = it))}, KeyboardType.Email)
            Fields("Contraseña", cliente.password, error, {viewModel.onClienteLoginChange(cliente.copy(password = it))}, KeyboardType.Password, Icons.Filled.VisibilityOff)
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    viewModel.onClienteLogin()
                    navController.navigate(Screen.Home.route)

                }, Modifier.fillMaxWidth(), enabled = activo) {
                    Text("Login")
                }
            }
            Text(
                text = "Si no tienes una cuenta, haz click para crearla",
                Modifier.clickable { navController.navigate(Screen.Registro.route) }
            )
        }
    }
}
