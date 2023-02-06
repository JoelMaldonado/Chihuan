package com.jjmf.chihuancompose.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.Repository.DeudaRepository
import com.jjmf.chihuancompose.Data.Repository.HistorialRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeudasViewModel @Inject constructor(
    private val repository: DeudaRepository,
    private val repoHistorial : HistorialRepository
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

    data class DeudaState(
        val cargando:Boolean = false,
        val listado : List<Deuda> = emptyList(),
        val alerta:Boolean = false
    )

    fun insertar(deuda: Deuda, historial: Historial) {
        viewModelScope.launch {
            val id = repository.insert(deuda)
            repoHistorial.insert(historial.copy(idDeuda = id))
        }
    }

    fun actualizarMonto(hist: Historial, deuda: Deuda) {
        viewModelScope.launch {
            repoHistorial.insert(hist)
            repository.update(deuda)
        }
    }
}