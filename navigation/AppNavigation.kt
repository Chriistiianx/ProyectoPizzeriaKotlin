package com.example.pizzazo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ui.login.LoginDisplay
import com.ui.login.LoginViewModel
import com.ui.registro.RegistroDisplay
import com.ui.registro.RegistroViewModel
import com.example.pizzazo.ui.home.HomeDisplay
import com.example.pizzazo.ui.home.HomeViewModel
import com.example.pizzazo.ui.home.NotificacionDialog

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // Pantalla inicial
    ) {
        composable(Screen.Login.route) {
            LoginDisplay(
                viewModel = LoginViewModel(),
                navController = navController
            )
        }
        composable(Screen.Registro.route) {
            RegistroDisplay(
                viewModel = RegistroViewModel(),
                navController = navController
            )
        }
        composable(Screen.Home.route) {
            HomeDisplay(viewModel = HomeViewModel(), navController = navController)

        }
    }
}