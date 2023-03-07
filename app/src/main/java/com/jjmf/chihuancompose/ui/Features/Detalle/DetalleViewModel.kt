package com.jjmf.chihuancompose.ui.Features.Detalle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.Repository.DeudaRepository
import com.jjmf.chihuancompose.Data.Repository.HistorialRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleViewModel @Inject constructor(
    private val repository: HistorialRepository,
    private val repoDeuda: DeudaRepository,
) : ViewModel() {

    var descrip by mutableStateOf("")
    var monto by mutableStateOf(TextFieldValue(text = "0", selection = TextRange(1)))

    var state by mutableStateOf(DetalleState())

    var bool by mutableStateOf(true)


    fun getList(idDeuda: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getListFlow(idDeuda).collect {
                state = state.copy(listado = it)
            }
        }
    }


    fun delete(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repoDeuda.delete(id)
            repository.deleteTotal(id)
        }
    }

    fun deleteHistorial(idHistorial:String?) {
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(idHistorial = idHistorial.toString())
        }
    }

    fun insertarRegistro(historial: Historial) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(historial)
            state = state.copy(alertaModificar = false)
        }
    }

}