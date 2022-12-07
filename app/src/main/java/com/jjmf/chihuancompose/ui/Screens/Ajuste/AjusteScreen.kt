package com.jjmf.chihuancompose.ui.Screens.Ajuste

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.chihuancompose.ui.Screens.Menu.Components.Titulo
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun AjusteScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(ColorP2),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Titulo("¡Preferencias!")
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 80f),
            color = Color.White
        ) {
            Box(modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
            ) {

            }
        }
    }
}