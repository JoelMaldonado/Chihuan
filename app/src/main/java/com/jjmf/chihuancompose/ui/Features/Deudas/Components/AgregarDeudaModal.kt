package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.components.CajaDinero
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun AgregarDeudaModal(
    close:()->Unit,
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TituloAgregarDeuda(titulo = "Agregar Deuda")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
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

            val enable = (if (esNumero(viewModel.monto.text)) viewModel.monto.text.toDouble() > 0 else false) && viewModel.titulo.isNotEmpty()
            Button(
                onClick = {
                    viewModel.insertar()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorP2,
                    contentColor = Color.White,
                    disabledBackgroundColor = Color.Gray.copy(0.5f)
                ),
                enabled = enable
            ) {
                if (viewModel.loader){
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }else{
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = if (enable) Color.White else Color.Gray.copy(0.5f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Aceptar", color = if (enable) Color.White else Color.Gray.copy(0.5f))
                }
            }
        }
    }
}
