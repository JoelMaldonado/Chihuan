package com.jjmf.chihuancompose.ui.Features.Reporte.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed


@Composable
fun CardReporte(diario: Diario) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val time = diario.getFecha(diario.time!!)

        Text(
            text = diario.descrip.toString(),
            color = Color.Black,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = "${time.diaNum}/${time.mes}/${time.anio.substring(2)}",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "S/" + diario.monto.toString(),
            color = if ((diario.monto ?: 0.0) < 0) ColorRed else ColorP2,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
    }
}