package com.jjmf.chihuancompose.ui.Screens.Informacion.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Util.toFecha
import com.jjmf.chihuancompose.ui.theme.ColorP1


@Composable
fun CardHistorial(historial: Historial) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Text(text = "Descripción: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
                Text(text = historial.descripcion.toString())
            }
            Row {
                Text(text = "Fecha: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
                Text(text = historial.fecha?.toDate().toFecha("dd/MM/yyyy"))
            }
            Row {
                Text(text = "Hora: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
                Text(text = historial.fecha?.toDate().toFecha("HH:mm"))
            }
        }
        historial.dinero?.let {
            Text(
                text = if (it >= 0) "+ S/$it" else "S/$it",
                fontSize = 18.sp,
                color = if (it >= 0) Color.Green else Color.Red,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}