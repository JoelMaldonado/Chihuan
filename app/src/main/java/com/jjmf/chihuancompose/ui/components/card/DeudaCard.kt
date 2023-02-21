package com.jjmf.chihuancompose.ui.components.card

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.Util.getFecha
import com.jjmf.chihuancompose.Util.redondear
import com.jjmf.chihuancompose.Util.toFecha
import com.jjmf.chihuancompose.ui.theme.ColorOrange
import com.jjmf.chihuancompose.ui.theme.ColorP1


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DeudaCard(
    modifier: Modifier = Modifier,
    deuda: Deuda,
    toDetalle: () -> Unit,
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        onClick = toDetalle,
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)){
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Nombre(deuda.titulo)
                Fecha(deuda.fecha)
                Spacer(modifier = Modifier.height(5.dp))
                Monto(dinero = deuda.dinero, modifier = Modifier.align(Alignment.End))
            }

            val dinero = deuda.dinero ?: 0.0
            val color = if (dinero < 0) Color.Red else ColorP1
            Box(modifier = Modifier
                .align(Alignment.TopEnd)
                .size(15.dp)
                .clip(CircleShape)
                .background(color))
        }
    }
}

@Composable
private fun Nombre(nombre: String?) {
    Text(
        text = nombre.toString(),
        color = ColorP1,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Monto(modifier: Modifier, dinero: Double?) {
    if (dinero != null) {
        Text(text = " S/${dinero.redondear()}", modifier = modifier, fontWeight = FontWeight.SemiBold)
    } else {
        Text(text = "Monto no agregado", modifier = modifier)
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