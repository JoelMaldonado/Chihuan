package com.jjmf.chihuancompose.ui.Features.Diario.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.ui.Features.Detalle.Components.toSimpleString
import com.jjmf.chihuancompose.ui.theme.ColorRed

@Composable
fun ItemDiario(diario: Diario) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = diario.hora.toString())
            Text(text = diario.descrip.toString())
        }
        Text(text = diario.monto.toString(), color = if ((diario.monto ?: 0.0) < 0) ColorRed else Color.Green)
    }
}
