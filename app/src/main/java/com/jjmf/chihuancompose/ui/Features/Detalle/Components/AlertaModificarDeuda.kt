package com.jjmf.chihuancompose.ui.Features.Detalle.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.Util.invertir
import com.jjmf.chihuancompose.Util.redondear
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.Features.Detalle.DetalleViewModel
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.CajaDescrip
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.Checks
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.TituloAgregarDeuda
import com.jjmf.chihuancompose.ui.components.CajaDinero
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun AlertaModificarDeuda(
    deuda: Deuda,
    viewModel: DetalleViewModel = hiltViewModel(),
) {
    Dialog(
        onDismissRequest = {
            viewModel.state = viewModel.state.copy(alertaModificar = false)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TituloAgregarDeuda(titulo = "Agregar Registro")
                CajaDinero(
                    valor = viewModel.monto,
                    newValor = { viewModel.monto = toNumero(it) }
                )
                CajaDescrip(
                    valor = viewModel.descrip,
                    newValor = {
                        viewModel.descrip = it
                    }
                )
                Checks(
                    bool = viewModel.bool,
                    newValor = {
                        viewModel.bool = it
                    }
                )
                BotonAgregarRegistro(deuda)
            }
        }
    }
}

@Composable
fun BotonAgregarRegistro(
    deuda: Deuda,
    viewModel: DetalleViewModel = hiltViewModel(),
) {
    Button(
        onClick = {
            if (deuda.segundo){
                val numero = viewModel.monto.text.toDouble().redondear().invertir()
                val historial = Historial(
                    fecha = Timestamp.now(),
                    dinero = if (viewModel.bool) numero else -numero,
                    descripcion = viewModel.descrip,
                    idDeuda = deuda.id
                )
                viewModel.insertarRegistro(historial)
            }else{
                val numero = viewModel.monto.text.toDouble().redondear()
                val historial = Historial(
                    fecha = Timestamp.now(),
                    dinero = if (viewModel.bool) numero else -numero,
                    descripcion = viewModel.descrip,
                    idDeuda = deuda.id
                )
                viewModel.insertarRegistro(historial)
            }
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
        enabled = (if (esNumero(viewModel.monto.text)) viewModel.monto.text.toDouble() > 0 else false) && viewModel.descrip.isNotEmpty()
    ) {
        Text(text = "Aceptar")
    }
}
