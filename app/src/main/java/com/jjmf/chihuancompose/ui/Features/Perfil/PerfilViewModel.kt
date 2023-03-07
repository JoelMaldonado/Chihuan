package com.jjmf.chihuancompose.ui.Features.Perfil

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Application.BaseApp
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var state by mutableStateOf(PerfilState())

    fun getUsuario() {
        viewModelScope.launch(Dispatchers.IO){
            val user =  repository.getListUsuarios().find { it.id == BaseApp.prefs.getId() }
            state = state.copy(usuario = user)
        }
    }
}