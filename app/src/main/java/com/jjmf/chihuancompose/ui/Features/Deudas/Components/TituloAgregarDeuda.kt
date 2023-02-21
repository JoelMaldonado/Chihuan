package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun TituloAgregarDeuda(titulo:String = "Agredar Deuda") {
    Text(
        text = titulo,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorP2)
            .padding(8.dp),
        textAlign = TextAlign.Center
    )
}