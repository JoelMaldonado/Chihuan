package com.jjmf.chihuancompose.ui.Features.Diario.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.CajaDescrip
import com.jjmf.chihuancompose.ui.Features.Diario.DiarioViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorP3
import com.jjmf.chihuancompose.ui.theme.ColorP4
import com.jjmf.chihuancompose.ui.theme.ColorRed


@Composable
fun AlertaIngreso(
    viewModel: DiarioViewModel = hiltViewModel(),
) {
    Dialog(
        onDismissRequest = {
            viewModel.state = viewModel.state.copy(alertaIngreso = false) }
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
                    text = "Registrar Ingreso",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ColorP3)
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
                    }
                )
                CajaDescrip(
                    valor = viewModel.descrip,
                    newValor = {
                        viewModel.descrip = it
                    }
                )
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            viewModel.state = viewModel.state.copy(alertaIngreso = false)
                                  },
                        border = BorderStroke(width = 1.dp, color = ColorP2),
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = "Cancelar", color = ColorP2)
                    }
                    Button(
                        onClick = {
                        viewModel.insertarIngreso()},
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorP2,
                            contentColor = Color.White,
                            disabledBackgroundColor = ColorP2.copy(0.5f)
                        ),
                        enabled = viewModel.monto.text.toDouble() != 0.0 && viewModel.descrip.isNotEmpty()
                    ) {
                        Text(text = "Registrar")
                    }
                }
            }
        }
    }
}

@Composable
fun CajaDinero(
    valor:TextFieldValue,
    newValor:(String)->Unit,
    color: Color = ColorP2
) {

    OutlinedTextField(
        value = valor,
        onValueChange = {
            if (esNumero(it.text) || it.text.isEmpty()) newValor(it.text)
        },
        label = { Text(text = "Monto") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        leadingIcon = {
            Text(
                text = "S/.",
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = color,
            cursorColor = color,
            focusedLabelColor = color,
            unfocusedBorderColor = Color.Gray,
            unfocusedLabelColor = Color.Gray
        ),
        textStyle = TextStyle(color = Color.Black)
    )
}
