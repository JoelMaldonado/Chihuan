package com.jjmf.chihuancompose.ui.Features.Registro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val repository: UsuarioRepository,
) : ViewModel() {

    var nombre by mutableStateOf("")
    var apellidos by mutableStateOf("")
    var correo by mutableStateOf("")
    var pass1 by mutableStateOf("")
    var pass2 by mutableStateOf("")
    var loader by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var pass by mutableStateOf(false)


    fun registrar() {
        viewModelScope.launch(Dispatchers.IO) {
            loader = true
            val usuario = Usuario(
                nombres = nombre,
                apellido = apellidos,
                correo = correo,
                pass = pass1
            )
            if (!repository.insert(usuario)){
                error = "El correo ya se encuentra en uso"
                loader = false
            }else{
                pass = true
                loader = false
            }
        }
    }
}