package com.jjmf.chihuancompose.ui.Features.Diario.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.CajaDescrip
import com.jjmf.chihuancompose.ui.Features.Diario.DiarioViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed


@Composable
fun AlertaGasto(
    viewModel: DiarioViewModel = hiltViewModel(),
) {
    Dialog(
        onDismissRequest = {
            viewModel.state = viewModel.state.copy(alertaGasto = false)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Registrar Gasto",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ColorRed)
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                CajaDinero(
                    valor = viewModel.monto,
                    newValor = {
                        viewModel.monto = toNumero(it)
                    },
                    color = ColorRed
                )
                CajaDescrip(
                    valor = viewModel.descrip,
                    newValor = {
                        viewModel.descrip = it
                    },
                    color = ColorRed
                )
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedButton(
                        onClick = {},
                        border = BorderStroke(width = 1.dp, color = ColorRed)
                    ) {
                        Text(text = "Cancelar", color = ColorRed)
                    }
                    Button(
                        onClick = {
                                  viewModel.insertarGasto()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorRed,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Registrar")
                    }
                }
            }
        }
    }
}
