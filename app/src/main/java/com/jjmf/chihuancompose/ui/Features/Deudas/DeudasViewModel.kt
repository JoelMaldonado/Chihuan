package com.jjmf.chihuancompose.ui.Features.Deudas

import androidx.compose.material.*
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
import kotlinx.coroutines.CoroutineScope
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

    @OptIn(ExperimentalMaterialApi::class)
    val bottomState = BottomSheetScaffoldState(
        drawerState = DrawerState(DrawerValue.Closed),
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed),
        snackbarHostState = SnackbarHostState()
    )

    lateinit var coroutine:CoroutineScope

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
                titulo = usuario.nombres.primero()
                idUsuario2 = usuario.id
                isQr = true
            }
        }
    }

    var loader by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun insertar() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
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
                closeOrOpen(false)
                titulo = ""
                monto = monto.copy(text = "0")
                descrip = ""
            }catch (e:Exception){
                loader = false
                error = e.message
            }finally {
                loader = false
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun closeOrOpen(open:Boolean) {
        if (open){
            coroutine.launch {
                bottomState.bottomSheetState.expand()
            }
        }else{
            coroutine.launch {
                bottomState.bottomSheetState.collapse()
            }
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