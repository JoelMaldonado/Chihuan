package com.jjmf.chihuancompose.ui.Features.Preferencias

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferenciaViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var state by mutableStateOf(PerfilState())

    fun getUsuario() {
        viewModelScope.launch(Dispatchers.IO){
            state = state.copy(usuario = prefs.getUser())
        }
    }
}