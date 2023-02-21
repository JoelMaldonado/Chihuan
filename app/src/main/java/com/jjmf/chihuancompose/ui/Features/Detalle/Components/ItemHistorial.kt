package com.jjmf.chihuancompose.ui.Features.Detalle.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.ui.theme.ColorRed
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ItemHistorial(historial: Historial) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = toSimpleString(historial.fecha?.toDate()))
            Text(text = historial.descripcion.toString())
        }
        Text(text = historial.dinero.toString(), color = if ((historial.dinero ?: 0.0) < 0) ColorRed else Color.Green)
    }
}

fun toSimpleString(date: Date?) : String {
    val format = SimpleDateFormat("dd/MM/yyyy")
    return if (date!=null) format.format(date) else "Sin fecha"
}