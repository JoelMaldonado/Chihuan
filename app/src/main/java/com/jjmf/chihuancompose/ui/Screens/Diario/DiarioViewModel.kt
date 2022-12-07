package com.jjmf.chihuancompose.ui.Screens.Diario

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.Data.UseCase.Diario.GetListDiarioFlow
import com.jjmf.chihuancompose.Data.UseCase.Diario.InsertDiario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiarioViewModel @Inject constructor(
    private val insertDiario: InsertDiario,
    private val getListDiarioFlow : GetListDiarioFlow
) : ViewModel() {

    var state = mutableStateOf<List<Diario>>(listOf())
        private set

    fun insert(diario: Diario) {
        viewModelScope.launch {
            insertDiario(diario)
        }
    }

    init {
        getList()
    }

    private fun getList(){
        viewModelScope.launch {
            getListDiarioFlow().collect(){
                state.value = it
            }
        }
    }
}