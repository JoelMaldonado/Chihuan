package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Application.BaseApp
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.Util.invertir
import com.jjmf.chihuancompose.Util.redondear
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun BotonInsertDeuda(
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    Button(
        onClick = {
            val numero = viewModel.monto.text.toDouble().redondear()
            val deuda = Deuda(
                titulo = viewModel.titulo,
                dinero = if (viewModel.bool) numero else numero.invertir(),
                fecha = Timestamp.now(),
                idUsuario = BaseApp.prefs.getId()
            )
            val historial = Historial(
                fecha = Timestamp.now(),
                dinero = deuda.dinero,
                descripcion = viewModel.descrip
            )
            viewModel.insertar(deuda, historial)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ColorP2,
            contentColor = Color.White,
            disabledBackgroundColor = Color.Gray.copy(0.5f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(20.dp),
        enabled = (if (esNumero(viewModel.monto.text)) viewModel.monto.text.toDouble() > 0 else false) && viewModel.titulo.isNotEmpty()
    ) {
        Text(text = "Aceptar")
    }
}
