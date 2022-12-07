package com.jjmf.chihuancompose.ui.Screens.Informacion

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.UseCase.Deuda.DeleteDeuda
import com.jjmf.chihuancompose.Data.UseCase.Historial.DeleteHistorial
import com.jjmf.chihuancompose.Data.UseCase.Historial.GetListHistorial
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class InformacionViewModel @Inject constructor(
    private val getListHistorial: GetListHistorial,
    private val deleteDeuda: DeleteDeuda,
    private val deleteHistorial: DeleteHistorial
) : ViewModel() {

    var state = mutableStateOf<List<Historial>>(listOf())
        private set

    fun getList(idDeuda: String){
        viewModelScope.launch {
            state.value = getListHistorial(idDeuda)
        }
    }

    fun delete(idDeuda:String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteDeuda(idDeuda)
            deleteHistorial(idDeuda)
        }
    }
}