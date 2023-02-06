package com.jjmf.chihuancompose.ui.Screens.Deudas.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Application.BaseApp
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.Util.invertir
import com.jjmf.chihuancompose.Util.redondear
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.Screens.Deudas.BotonAgregarDeuda
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.viewModel.DeudasViewModel

@Composable
fun AgregarDeudaAlerta(
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    Dialog(
        onDismissRequest = {
            viewModel.state = viewModel.state.copy(alerta = false)
        }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Surface(
                    shape = RoundedCornerShape(bottomStart = 50.dp),
                    modifier = Modifier.fillMaxWidth(),
                    color = ColorP2
                ) {
                    Text(
                        text = "Agredar Deuda",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }

                OutlinedTextField(
                    value = viewModel.titulo,
                    onValueChange = { viewModel.titulo = it },
                    label = { Text(text = "Titulo") }
                )
                OutlinedTextField(
                    value = viewModel.monto,
                    onValueChange = {
                        if (esNumero(it.text) || it.text.isEmpty()) viewModel.monto = toNumero(it.text)
                    },
                    label = { Text(text = "Monto") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = { Text(text = "S/.") }
                )
                OutlinedTextField(
                    value = viewModel.descrip,
                    onValueChange = { viewModel.descrip = it },
                    label = { Text(text = "Descripción (Opcional)") }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = viewModel.bool,
                        onClick = { viewModel.bool = true }
                    )
                    Text(text = "Prestamo")
                    Spacer(modifier = Modifier.width(5.dp))
                    RadioButton(
                        selected = !viewModel.bool,
                        onClick = {
                            viewModel.bool = false
                        }
                    )
                    Text(text = "Recibir")
                }
                BotonInsertDeuda()
            }
        }
    }
}

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
            viewModel.state = viewModel.state.copy(alerta = false)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = ColorP1),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(20.dp),
        enabled = (if (esNumero(viewModel.monto.text)) viewModel.monto.text.toDouble() > 0 else false) && viewModel.titulo.isNotEmpty()
    ) {
        Text(text = "Aceptar")
    }
}
