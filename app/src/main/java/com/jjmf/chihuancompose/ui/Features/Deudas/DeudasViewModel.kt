package com.jjmf.chihuancompose.ui.Features.Deudas

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.DeudaRepository
import com.jjmf.chihuancompose.Data.Repository.HistorialRepository
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeudasViewModel @Inject constructor(
    private val repository: DeudaRepository,
    private val repoHistorial : HistorialRepository,
    private val repoUsuario:UsuarioRepository
) : ViewModel() {



    var state by mutableStateOf(DeudaState())

    var titulo by mutableStateOf("")
    var monto by mutableStateOf(TextFieldValue(text = "0", selection = TextRange(1)))
    var descrip by mutableStateOf("")
    var bool by mutableStateOf(true)

    init {
        viewModelScope.launch {
            repository.getList().collect(){
                state = state.copy(
                    listado = it
                )
            }
        }
    }


    fun insertar(deuda: Deuda, historial: Historial) {
        viewModelScope.launch {
            val id = repository.insert(deuda)
            repoHistorial.insert(historial.copy(idDeuda = id))
            state = state.copy(alerta = false)
        }
    }
}