package com.jjmf.chihuancompose.ui.Features.Detalle.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.Features.Detalle.DetalleViewModel
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.CajaDescrip
import com.jjmf.chihuancompose.ui.Features.Diario.Components.CajaDinero
import com.jjmf.chihuancompose.ui.theme.ColorOrange

@Composable
fun ModificarDeudaBottom(
    deuda: Deuda,
    viewModel: DetalleViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(bottomStart = 50.dp),
            modifier = Modifier.fillMaxWidth(),
            color = ColorP2
        ) {
            Text(
                text = "Modificar Monto",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
        }

        CajaDinero(
            valor = viewModel.monto,
            newValor = {
                viewModel.monto = toNumero(it)
            }
        )
        CajaDescrip(
            valor = viewModel.descrip,
            newValor = { viewModel.descrip = it }
        )

        Checks2()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    viewModel.close()
                },
                modifier = Modifier
                    .weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorOrange,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cancelar", fontWeight = FontWeight.SemiBold)
            }
            Button(
                onClick = {
                    viewModel.modificar(deuda)
                },
                modifier = Modifier
                    .weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorP2,
                    disabledBackgroundColor = Color.Gray.copy(0.5f),
                    contentColor = Color.White
                ),
                enabled = if (esNumero(viewModel.monto.text)) viewModel.monto.text.toDouble() > 0 else false
            ) {
                Text(text = "Aceptar", fontWeight = FontWeight.SemiBold)
            }
        }

    }

}
