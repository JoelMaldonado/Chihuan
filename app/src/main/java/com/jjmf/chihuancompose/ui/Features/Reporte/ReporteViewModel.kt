package com.jjmf.chihuancompose.ui.Features.Reporte

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.rememberImagePainter
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.Data.Repository.DiarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReporteViewModel @Inject constructor(
    private val repository: DiarioRepository,
) : ViewModel() {

    var state by mutableStateOf(ReporteState())

    fun get() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getList(prefs.getId()).collect() { list ->
                state = state.copy(
                    listado = list,
                    ingresos = list.filter { (it.monto ?: 0.0) > 0.0 }.sumOf { it.monto ?:0.0},
                    gastos = list.filter { (it.monto ?: 0.0) < 0.0 }.sumOf { it.monto ?:0.0},
                    total = list.sumOf { it.monto ?:0.0}
                )
            }
        }
    }
}