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
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Application.BaseApp
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.Util.invertir
import com.jjmf.chihuancompose.Util.redondear
import com.jjmf.chihuancompose.Util.toNumero
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun AgregarDeudaAlerta(click: (Deuda?, Observacion: String) -> Unit) {
    var titulo by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf(TextFieldValue(text = "0", selection = TextRange(1))) }
    var descrip by remember { mutableStateOf("") }
    var bool by remember { mutableStateOf(true) }
    Dialog(onDismissRequest = { click(null, descrip) }) {
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
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text(text = "Titulo") }
                )
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = bool,
                        onClick = { bool = true }
                    )
                    Text(text = "Enviar")
                    Spacer(modifier = Modifier.width(5.dp))
                    RadioButton(selected = !bool, onClick = { bool = false })
                    Text(text = "Recibir")
                }
                Button(
                    onClick = {
                        val numero = monto.text.toDouble().redondear()
                        val deuda = Deuda(
                            titulo = titulo,
                            dinero = if (bool) numero else numero.invertir(),
                            fecha = Timestamp.now(),
                            idUsuario = BaseApp.prefs.getId()
                        )
                        click(deuda, descrip)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = ColorP1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    shape = RoundedCornerShape(20.dp),
                    enabled = (if (esNumero(monto.text)) monto.text.toDouble() > 0 else false ) && titulo.isNotEmpty()
                ) {
                    Text(text = "Aceptar")
                }
            }
        }
    }
}