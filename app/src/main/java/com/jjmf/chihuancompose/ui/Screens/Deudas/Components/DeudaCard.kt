package com.jjmf.chihuancompose.ui.Screens.Deudas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.Util.getFecha
import com.jjmf.chihuancompose.Util.redondear
import com.jjmf.chihuancompose.Util.toFecha
import com.jjmf.chihuancompose.ui.Screens.Deudas.Components.ModificarDeudaAlerta
import com.jjmf.chihuancompose.ui.theme.ColorOrange
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorS1


@Composable
fun DeudaCard(
    modifier: Modifier,
    deuda: Deuda,
    viewModel: DeudasViewModel,
    toInformacion: (String) -> Unit,
) {
    var bool by remember { mutableStateOf(false) }
    var code by remember { mutableStateOf(0) }

    if (bool) ModificarDeudaAlerta(code = code, deuda) { bool = false }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp), elevation = 5.dp, shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(Modifier.weight(1f)) {
                Nombre(deuda.titulo)
                Monto(deuda.dinero)
                Fecha(deuda.fecha)
                TextButton(onClick = {
                    toInformacion(deuda.id!!)
                }) {
                    Text(
                        text = "Mas información",
                        color = ColorS1,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = ColorS1
                    )
                }
            }
            Recibir {
                bool = true
                code = 2
            }
            Spacer(modifier = Modifier.width(10.dp))
            Dar {
                bool = true
                code = 1
            }

        }
    }
}

@Composable
private fun Nombre(nombre: String?) {
    Text(
        text = nombre.toString(),
        color = ColorP1,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

@Composable
private fun Monto(dinero: Double?) {
    Row {
        if (dinero != null) {
            if (dinero < 0) {
                Text(
                    text = "Deuda de:",
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Text(
                    text = "Te debe:",
                    color = ColorP1,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(text = " S/.${dinero.redondear()}")
        } else {
            Text(text = "Monto no agregado")
        }
    }
}

@Composable
private fun Fecha(fecha: Timestamp?) {
    if (fecha != null) {
        val hoy = getFecha("dd").toInt()
        val ayer = getFecha("dd").toInt() - 1
        val diaFecha = fecha.toDate().toFecha("dd").toInt()

        val hora = fecha.toDate().toFecha("HH").toInt()
        val apm = if (hora <= 12) {
            "${fecha.toDate().toFecha("HH:mm")} am"
        } else {
            val nueva = hora - 12
            "$nueva:${fecha.toDate().toFecha("mm")} pm"
        }
        when (diaFecha) {
            hoy -> {
                Text(text = "Hoy $apm")
            }
            ayer -> {
                Text(text = "Ayer $apm")
            }
            else -> {
                Text(text = "${fecha.toDate().toFecha("dd/MM")} $apm")
            }
        }
    } else {
        Text(text = "Fecha no encontrada")
    }
}

@Composable
private fun Dar(click: () -> Unit) {
    FloatingActionButton(onClick = { click() }, backgroundColor = ColorOrange) {
        Icon(painter = painterResource(id = R.drawable.ic_dar),
            contentDescription = null,
            tint = Color.White)
    }
}

@Composable
private fun Recibir(click: () -> Unit) {
    FloatingActionButton(onClick = { click() }, backgroundColor = ColorP1) {
        Icon(painter = painterResource(id = R.drawable.ic_recibir),
            contentDescription = null,
            tint = Color.White)
    }
}