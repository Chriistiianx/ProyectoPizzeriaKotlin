package com.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pizzazo.data.ErrorMessage
import com.example.pizzazo.data.LoginDTO

class LoginViewModel {
    val cliente = MutableLiveData<LoginDTO>()
    var errorMsg = MutableLiveData<ErrorMessage>()
    var isActivo = MutableLiveData(false)

    fun onClienteLoginChange(newLogin: LoginDTO){
        var email = false
        var pass = false
        errorMsg.value = ErrorMessage("",
            email = (if (newLogin.email.isNotBlank() && !newLogin.email.matches(Regex("^[\\w.-]+@[\\w-]+\\.[A-Za-z]{2,4}$"))) email = false else email = true).toString(),
            password = (if (newLogin.password.isNotBlank()) pass = true else pass = false).toString()
        )

        if (email && pass) {
            isActivo.value = true
        } else isActivo.value = false

        cliente.value = newLogin

    }

    fun onClienteLogin(){
        Log.d("botonLogear", "Cliente: ${cliente.value}")
    }
}