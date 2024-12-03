package com.ui.registro

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aplicacionpizzeria.modelo.ClienteDTO
import com.example.pizzazo.data.ErrorMessage

class RegistroViewModel : ViewModel() {
    var cliente = MutableLiveData<ClienteDTO>()
    var botonRegistro = MutableLiveData(false)
    var errorMsg = MutableLiveData<ErrorMessage>()

    fun onRegisterCliente(){
        Log.d("botonRegistro", "Cliente: ${cliente.value}")
    }

    fun onClienteChange(newCliente: ClienteDTO) {
        errorMsg.value = ErrorMessage(
            (if (newCliente.nombre.isNotBlank() && !newCliente.nombre.matches("[a-zA-Z]+".toRegex())) "El nombre no puede contener dígitos." else "").toString(),
            (if (newCliente.email.isNotBlank() && !newCliente.email.matches(Regex("^[\\w.-]+@[\\w-]+\\.[A-Za-z]{2,4}$"))) "No es un email correcto." else "").toString(),
            (if (newCliente.password.isNotBlank() && !newCliente.password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$".toRegex())) "La contraseña tiene que tener al menos 4 caracteres." else "").toString()
        )

        if (newCliente.dni.isBlank() || newCliente.nombre.isBlank() || newCliente.email.isBlank() || newCliente.password.isBlank() || newCliente.telefono.isBlank() || newCliente.direccion.isBlank()) {
            botonRegistro.value = false
        } else botonRegistro.value = true

        cliente.value = newCliente
    }
}