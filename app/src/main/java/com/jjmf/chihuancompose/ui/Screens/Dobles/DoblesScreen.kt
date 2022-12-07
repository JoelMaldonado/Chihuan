package com.jjmf.chihuancompose.ui.Screens.Dobles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.chihuancompose.ui.Screens.Menu.Components.Titulo
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun DoblesScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(ColorP2),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Titulo("¡Deudas Compartidas!")
        Cuerpo(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                BotonAgredarDoble {

                }
            }
        }
    }
}

@Composable
fun Cuerpo(modifier: Modifier, comp: @Composable () -> Unit) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 80f),
        color = Color.White,
        content = comp
    )
}

@Composable
fun BotonAgredarDoble(click: () -> Unit) {
    FloatingActionButton(onClick = { click() }, backgroundColor = ColorP1) {
        Icon(imageVector = Icons.Default.PostAdd, contentDescription = null, tint = Color.White)
    }
}
