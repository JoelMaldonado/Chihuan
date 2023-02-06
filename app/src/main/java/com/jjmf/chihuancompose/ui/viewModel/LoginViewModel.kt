package com.jjmf.chihuancompose.ui.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
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

    fun insertar(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO){
            val list = repository.getListUsuarios().map { it.correo }
            Log.d("Tagito", list.toString())
            if (usuario.correo in list) repository.insert(usuario)
        }
    }
    var state by mutableStateOf(LoginState())
    data class LoginState(
        val cargando:Boolean = false,
        val usuario: Usuario? = null,
        val mensajeError:String? = null
    )

    fun getId(email: String) {
        viewModelScope.launch(Dispatchers.IO){
            val usu = repository.getListUsuarios().find { it.correo == email }
            state = if (usu!=null){
                if (usu.id != null){
                    prefs.saveID(usu.id!!)
                    state.copy(usuario = usu)
                }else{
                    state.copy(mensajeError = "No se encontro Id del usuario")
                }
            }else{
                state.copy(mensajeError = "Usuario no encontrado")
            }
        }
    }
}