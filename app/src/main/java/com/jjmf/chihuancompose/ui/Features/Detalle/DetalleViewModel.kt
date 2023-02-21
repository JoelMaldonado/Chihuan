package com.jjmf.chihuancompose.ui.Features.Detalle

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.Repository.DeudaRepository
import com.jjmf.chihuancompose.Data.Repository.HistorialRepository
import com.jjmf.chihuancompose.Util.redondear
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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

    var state by mutableStateOf<List<Historial>>(emptyList())

    var bool by mutableStateOf(true)

    @OptIn(ExperimentalMaterialApi::class)
    lateinit var bottomState: BottomSheetScaffoldState
    lateinit var coroutine: CoroutineScope


    fun getList(idDeuda: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getListFlow(idDeuda).collect {
                state = it
            }
        }
    }


    fun actualizarMonto(hist: Historial, deuda: Deuda) {
        viewModelScope.launch {
            repository.insert(hist)
            repoDeuda.update(deuda)
            close()
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun close() {
        coroutine.launch {
            bottomState.bottomSheetState.collapse()
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun open() {
        coroutine.launch {
            bottomState.bottomSheetState.expand()
        }
    }

    fun delete(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repoDeuda.delete(id)
            repository.delete(id)
        }
    }

    fun modificar(deuda: Deuda) {
        viewModelScope.launch(Dispatchers.IO){
            val historial = Historial(
                fecha = Timestamp.now(),
                dinero = if (bool) -monto.text.toDouble() else monto.text.toDouble(),
                descripcion = descrip,
                idDeuda = deuda.id
            )
            val nuevoValor = if (bool) {
                deuda.dinero?.minus(monto.text.toDouble())?.redondear()
            } else {
                deuda.dinero?.plus(monto.text.toDouble())?.redondear()
            }
            val deudaNuevo = deuda.copy(dinero = nuevoValor, fecha = Timestamp.now())
            repository.insert(historial)
            repoDeuda.update(deudaNuevo)
            close()
        }
    }

}