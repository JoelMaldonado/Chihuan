package com.jjmf.chihuancompose.ui.Screens.Diario.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.Util.*
import com.jjmf.chihuancompose.ui.Screens.Diario.DiarioViewModel
import com.jjmf.chihuancompose.ui.theme.ColorOrange
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorP4


@Composable
fun AlertaDiario(viewModel: DiarioViewModel = hiltViewModel(), dismiss: () -> Unit) {
    var monto by remember { mutableStateOf(TextFieldValue(text = "0", selection = TextRange(1))) }
    var descrip by remember { mutableStateOf("") }

    val focus = LocalFocusManager.current

    val numero = monto.text.toDouble().redondear()
    val diario = Diario(
        idUser = prefs.getId(),
        descrip = descrip,
        fecha = getFecha("dd/MM/yyyy"),
        hora = getFecha("HH:mm")
    )

    Dialog(onDismissRequest = { dismiss() }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = 5.dp
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
                        text = "Movimiento",
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    leadingIcon = { Text(text = "S/.") },
                    maxLines = 1,
                    keyboardActions = KeyboardActions(
                        onNext = { focus.moveFocus(FocusDirection.Down) }
                    )
                )

                OutlinedTextField(
                    value = descrip,
                    onValueChange = { descrip = it },
                    label = { Text(text = "Descripción (Opcional)") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focus.clearFocus() }
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Button(
                        onClick = {
                            viewModel.insert(diario.copy(monto = numero))
                            dismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorP4,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(15.dp),
                        enabled = if (esNumero(monto.text)) monto.text.toDouble() > 0 else false
                    ) {
                        Text(text = "Ingreso")
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                    Button(
                        onClick = {
                            viewModel.insert(diario.copy(monto = numero.invertir()))
                            dismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorOrange,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(15.dp),
                        enabled = if (esNumero(monto.text)) monto.text.toDouble() > 0 else false
                    ) {
                        Text(text = "Egreso")
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(imageVector = Icons.Default.Remove, contentDescription = null)
                    }
                }
            }
        }
    }
}