package com.jjmf.chihuancompose.ui.Features.Diario

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.Data.Repository.DiarioRepository
import com.jjmf.chihuancompose.Util.getDia
import com.jjmf.chihuancompose.Util.getFecha
import com.jjmf.chihuancompose.Util.getFecha2
import com.jjmf.chihuancompose.Util.getHora
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DiarioViewModel @Inject constructor(
    private val repository: DiarioRepository,
) : ViewModel() {
    var state by mutableStateOf(DiarioState())

    var monto by mutableStateOf(TextFieldValue(text = "0", selection = TextRange(1)))
    var descrip by mutableStateOf("")

    fun insertarGasto() {
        viewModelScope.launch(Dispatchers.IO) {
            val diario = Diario(
                monto = -monto.text.toDouble(),
                descrip = descrip,
                idUsuario = prefs.getId(),
                time = Timestamp.now()
            )
            repository.insert(diario)
            state = state.copy(alertaGasto = false)
        }
    }

    fun insertarIngreso() {
        viewModelScope.launch(Dispatchers.IO) {
            val diario = Diario(
                monto = monto.text.toDouble(),
                descrip = descrip,
                idUsuario = prefs.getId(),
                time = Timestamp.now(),
            )
            repository.insert(diario)
            state = state.copy(alertaIngreso = false)
        }
    }


    fun reset() {
        monto = TextFieldValue(text = "0", selection = TextRange(1))
        descrip = ""
    }

    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getListByDia(prefs.getId()).collect {
                state = state.copy(
                    listado = it.sortedByDescending { it.time },
                    contador = it.sumOf { it.monto ?: 0.0 }
                )
            }
        }
    }

    fun eliminarDiario(idDiario: String?) {
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(idDiario = idDiario)
        }
    }

}