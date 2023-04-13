package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.components.CajaDinero

@Composable
fun AgregarDeudaAlerta(
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as Activity
    Dialog(
        onDismissRequest = {
            viewModel.state = viewModel.state.copy(alerta = false)
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
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TituloAgregarDeuda(titulo = "Agregar Deuda")
                CajaTitulo()
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

                EscanearCodigo(
                    activity = activity
                )

                BotonInsertDeuda()
            }
        }
    }
}
