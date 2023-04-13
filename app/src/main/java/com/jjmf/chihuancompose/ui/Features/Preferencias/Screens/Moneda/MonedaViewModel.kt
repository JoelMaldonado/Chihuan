package com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Moneda

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Core.PaisesApi
import com.jjmf.chihuancompose.Data.Model.Pais
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonedaViewModel @Inject constructor(
) : ViewModel() {

    var filtro by mutableStateOf("")
    var list by mutableStateOf<List<Pais>>(emptyList())
    var listFiltro by mutableStateOf<List<Pais>>(emptyList())

    var error by mutableStateOf<String?>(null)
    var cargando by mutableStateOf(false)

    fun getPaises() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                cargando = true
                val call = PaisesApi.retro().getAll()

                if (call.isSuccessful){
                    val body = call.body()
                    if (body!=null){
                        list = body.filter { it.fullName != "Antarctica" }
                        listFiltro = list
                    }else{
                        error = "Sin listado"
                    }
                }else error = call.message()
            }catch (e:Exception){
                error = e.message
            }finally {
                cargando = false
            }
        }
    }

}