package com.jjmf.chihuancompose.ui.Features.Diario.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.ui.Features.Detalle.Components.toSimpleString
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed

@Composable
fun ItemDiario(diario: Diario) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Hora: "+ diario.hora.toHora())
            Text(text = "Descripción: " + diario.descrip.toString())
        }
        Text(
            text = "S/" + diario.monto.toString(),
            color = if ((diario.monto ?: 0.0) < 0) ColorRed else ColorP2,
            fontWeight = FontWeight.SemiBold
        )
    }
}

private fun String?.toHora(): String {
    return if (this != null) {
        val palabra = this.split(":")
        val primero = palabra[0].toInt()
        val segundo = palabra[1]
        if (primero>=12){
            "${primero-12}:$segundo pm"
        }else this

    }else{
        "Sin Hora"
    }
}
