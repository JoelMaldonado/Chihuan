package com.jjmf.chihuancompose.ui.components.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.Util.getFecha
import com.jjmf.chihuancompose.Util.invertir
import com.jjmf.chihuancompose.Util.toFecha
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.Features.Deudas.primero
import com.jjmf.chihuancompose.ui.theme.ColorOrange
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeudaCard(
    modifier: Modifier = Modifier,
    deuda: Deuda,
    toDetalle: () -> Unit,
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        onClick = toDetalle,
        backgroundColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (deuda.doble){
                    if (deuda.segundo){
                        val nombre = viewModel.getUsuario(deuda.idUsuario).collectAsState(initial = null).value
                        Nombre(nombre?.nombres.primero())
                    }else{
                        val nombre = viewModel.getUsuario(deuda.idUsuario2).collectAsState(initial = null).value
                        Nombre(nombre?.nombres.primero())
                    }
                }else{
                    Nombre(deuda.titulo)
                }
                Spacer(modifier = Modifier.height(5.dp))
                if (deuda.segundo){
                    Monto(deuda = deuda, modifier = Modifier.align(Alignment.End), neg = true)
                }else{
                    Monto(deuda = deuda, modifier = Modifier.align(Alignment.End))
                }
            }
        }
    }
}

@Composable
private fun Nombre(nombre: String?) {
    Text(
        text = nombre.toString(),
        color = Color.Black,
        fontWeight = FontWeight.Medium
    )
}

@Composable
private fun Monto(
    modifier: Modifier,
    deuda: Deuda,
    neg:Boolean = false,
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    val total = viewModel.getTotal(deuda.id ?: "").collectAsState(initial = 0.0).value
    val num = if (neg) total.invertir() else total

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = if (num<0) "Debes" else "Te debe")

        val symbol = prefs.getMoneda()?.symbol ?: "$"
        Text(
            text = " $symbol $num",
            modifier = modifier,
            fontWeight = FontWeight.SemiBold,
            color = if (num < 0) ColorRed else ColorP2
        )
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
                Text(text = "Hoy $apm", color = Color.Black)
            }
            ayer -> {
                Text(text = "Ayer $apm", color = Color.Black)
            }
            else -> {
                Text(text = "${fecha.toDate().toFecha("dd/MM")} $apm", color = Color.Black)
            }
        }
    } else {
        Text(text = "Fecha no encontrada")
    }
}

@Composable
private fun Dar(click: () -> Unit) {
    FloatingActionButton(onClick = { click() }, backgroundColor = ColorP1) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dar),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
private fun Recibir(click: () -> Unit) {
    FloatingActionButton(onClick = { click() }, backgroundColor = ColorOrange) {
        Icon(
            painter = painterResource(id = R.drawable.ic_recibir),
            contentDescription = null,
            tint = Color.White
        )
    }
}