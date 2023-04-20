package com.jjmf.chihuancompose.ui.Features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private  val repository: UsuarioRepository
) : ViewModel(){

    var correo by mutableStateOf("")
    var clave by mutableStateOf("")
    var state by mutableStateOf(LoginState())
    fun insertar(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO){
            if (!repository.insert(usuario)){
                val user= repository.getListUsuarios().find { it.correo == usuario.correo }
                if (user!=null){
                    prefs.saveUser(user)
                }else{
                    mensaje = "No se pudo encontrar el usuario"
                }
            }
            state = state.copy(toMenu = true)
        }
    }
    data class LoginState(
        val cargando:Boolean = false,
        val usuario: Usuario? = null,
        val mensajeError:String? = null,
        val toMenu:Boolean = false
    )

    var loader by mutableStateOf(false)
    var toMenu by mutableStateOf(false)
    var mensaje by mutableStateOf<String?>(null)

    fun login() {
        viewModelScope.launch(Dispatchers.IO){
            loader = true
            val user = repository.getListUsuarios().find { it.correo == correo && it.pass == clave }
            if (user!=null){
                toMenu = true
                loader = false
                prefs.saveUser(user)
            }else{
                mensaje = "Usuario no encontrado"
                loader = false
            }
        }
    }
}