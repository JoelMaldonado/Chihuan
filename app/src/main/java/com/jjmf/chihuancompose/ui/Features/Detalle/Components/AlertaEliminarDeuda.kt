package com.jjmf.chihuancompose.ui.Features.Detalle.Components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Detalle.DetalleViewModel


@Composable
fun AlertaEliminarDeuda(
    deuda: Deuda,
    back: () -> Unit,
    viewModel: DetalleViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
        titleText = "Eliminar Deuda"
        contentText = "Se elimininara '${deuda.titulo}', ¿Estas seguro?"
        setConfirmButton("Eliminar") {
            dismissWithAnimation()
            viewModel.delete(deuda.id!!)
            viewModel.state = viewModel.state.copy(alertaEliminar = false)
            back()
        }
        setCancelButton("Cancelar") {
            viewModel.state = viewModel.state.copy(alertaEliminar = false)
            dismissWithAnimation()
        }
        show()
    }
}