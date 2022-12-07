package com.jjmf.chihuancompose.ui.Screens.Deudas

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.UseCase.Deuda.GetListDeudaFlow
import com.jjmf.chihuancompose.Data.UseCase.Deuda.InsertDeuda
import com.jjmf.chihuancompose.Data.UseCase.Deuda.UpdateDeuda
import com.jjmf.chihuancompose.Data.UseCase.Historial.InsertHistorial
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeudasViewModel @Inject constructor(
    private val getListDeudaFlow: GetListDeudaFlow,
    private val insertDeuda: InsertDeuda,
    private val updateDeuda: UpdateDeuda,
    private val insertHistorial: InsertHistorial
) : ViewModel() {


    var state = mutableStateOf<List<Deuda>>(listOf())
        private set

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            getListDeudaFlow().collect() {list->
                state.value = list.filter { it.idUsuario == prefs.getId() }
            }
        }
    }

    fun insertar(deuda: Deuda, historial: Historial) {
        viewModelScope.launch {
            val id = insertDeuda.invoke(deuda)
            insertHistorial(historial.copy(idDeuda = id))
        }
    }

    fun actualizarMonto(hist: Historial, deuda: Deuda) {
        viewModelScope.launch {
            insertHistorial(hist)
            updateDeuda(deuda)
        }
    }
}