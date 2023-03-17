package com.jjmf.chihuancompose.ui.Features.Reporte

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Repository.DiarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReporteViewModel @Inject constructor(
    private val repository: DiarioRepository,
) : ViewModel() {

    var state by mutableStateOf(ReporteState())

    fun get() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getList(prefs.getUser()?.id!!).collect() { list ->
                state = state.copy(
                    listado = list,
                    ingresos = list.filter { (it.monto ?: 0.0) > 0.0 }.sumOf { it.monto ?:0.0},
                    gastos = list.filter { (it.monto ?: 0.0) < 0.0 }.sumOf { it.monto ?:0.0}
                )
            }
        }
    }
}