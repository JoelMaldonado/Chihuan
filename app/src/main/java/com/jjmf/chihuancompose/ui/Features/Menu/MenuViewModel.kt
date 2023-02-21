package com.jjmf.chihuancompose.ui.Features.Menu

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Application.BaseApp
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repoUsuario: UsuarioRepository
) : ViewModel() {

    var state by mutableStateOf(MenuState())

    fun getUsuario() {
        viewModelScope.launch(Dispatchers.IO){
            val user =  repoUsuario.getListUsuarios().find { it.id == BaseApp.prefs.getId() }
            state = state.copy(usuario = user)
        }
    }

}