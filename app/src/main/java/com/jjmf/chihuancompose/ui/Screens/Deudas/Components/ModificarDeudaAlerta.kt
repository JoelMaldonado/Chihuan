package com.jjmf.chihuancompose.ui.Screens.Deudas.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.Util.redondear
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.Screens.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun ModificarDeudaAlerta(
    code: Int,
    deuda: Deuda,
    viewModel: DeudasViewModel = hiltViewModel(),
    dismiss: () -> Unit,
) {
    var monto by remember { mutableStateOf(TextFieldValue(text = "0", selection = TextRange(1))) }
    var descrip by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { dismiss() }) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp
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

                OutlinedTextField(
                    value = monto,
                    onValueChange = {
                        if (esNumero(it.text) || it.text.isEmpty()) monto = toNumero(it.text)
                    },
                    label = { Text(text = "Monto") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = { Text(text = "S/.") }
                )
                OutlinedTextField(
                    value = descrip,
                    onValueChange = { descrip = it },
                    label = { Text(text = "Descripción (Opcional)") }
                )

                Button(
                    onClick = {
                        val historial = Historial(
                            fecha = Timestamp.now(),
                            dinero = deuda.dinero,
                            descripcion = descrip,
                            idDeuda = deuda.id
                        )
                        val nuevoValor = if (code == 1) {
                            deuda.dinero?.plus(monto.text.toDouble())?.redondear()
                        } else {
                            deuda.dinero?.minus(monto.text.toDouble())?.redondear()
                        }
                        val deudaNuevo = deuda.copy(dinero = nuevoValor, fecha = Timestamp.now())
                        viewModel.actualizarMonto(historial, deudaNuevo)
                        dismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    shape = RoundedCornerShape(20.dp),
                    enabled = if (esNumero(monto.text)) monto.text.toDouble() > 0 else false
                ) {
                    Text(text = "Aceptar", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
