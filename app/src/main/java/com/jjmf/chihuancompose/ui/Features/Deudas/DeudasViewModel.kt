package com.jjmf.chihuancompose.ui.Features.Deudas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.DeudaRepository
import com.jjmf.chihuancompose.Data.Repository.HistorialRepository
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import com.jjmf.chihuancompose.Util.redondear
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeudasViewModel @Inject constructor(
    private val repository: DeudaRepository,
    private val repoHistorial: HistorialRepository,
    private val repoUsuario: UsuarioRepository,
) : ViewModel() {


    var state by mutableStateOf(DeudaState())

    var titulo by mutableStateOf("")
    var idUsuario2 by mutableStateOf<String?>(null)
    var monto by mutableStateOf(TextFieldValue(text = "0", selection = TextRange(1)))
    var descrip by mutableStateOf("")
    var bool by mutableStateOf(true)
    var isQr by mutableStateOf(false)

    init {
        viewModelScope.launch {
            repository.getList().collect() {
                state = state.copy(listado = it)
            }
        }
    }




    fun getTotal(idDeuda: String): Flow<Double> = flow {
        emit(repoHistorial.getList(idDeuda = idDeuda).sumOf { it.dinero ?: 0.0 })
    }

    fun searchId(qr: String) {
        viewModelScope.launch(Dispatchers.IO){
            val usuario = repoUsuario.getListUsuarios().find { it.id == qr }
            if (usuario!=null){
                titulo = usuario.nombres.primero() + " " + usuario.apellido.primero()
                idUsuario2 = usuario.id
                isQr = true
            }
        }
    }
    fun insertar() {
        viewModelScope.launch(Dispatchers.IO) {
            val numero = monto.text.toDouble().redondear()
            val deuda = Deuda(
                titulo = titulo,
                fecha = Timestamp.now(),
                idUsuario = prefs.getUser()?.id,
                doble = false
            )

            val idDeuda = repository.insert(deuda)

            val historial = Historial(
                fecha = Timestamp.now(),
                dinero = if (bool) numero else -numero,
                descripcion = descrip,
                idDeuda = idDeuda
            )
            repoHistorial.insert(historial)
            state = state.copy(alerta = false)
        }
    }

    fun insertarCompartida() {
        viewModelScope.launch(Dispatchers.IO) {
            val numero = monto.text.toDouble().redondear()
            val deuda = Deuda(
                fecha = Timestamp.now(),
                idUsuario = prefs.getUser()?.id,
                idUsuario2 = idUsuario2,
                doble = true
            )

            val idDeuda = repository.insertDoble(deuda)

            val historial = Historial(
                fecha = Timestamp.now(),
                dinero = if (bool) numero else -numero,
                descripcion = descrip,
                idDeuda = idDeuda
            )
            repoHistorial.insert(historial)
            state = state.copy(alerta = false)
        }
    }

    fun getUsuario(idUsuario2: String?): Flow<Usuario?> = flow{
        emit(repoUsuario.getListUsuarios().find { it.id == idUsuario2 })
    }
}

fun String?.primero() : String{
    return if (this!=null && this.isNotEmpty()){
        this.split(" ")[0]
    }else ""
}