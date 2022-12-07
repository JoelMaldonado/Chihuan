package com.jjmf.chihuancompose.ui.Screens.Menu.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.ui.theme.ColorP4


@Composable
fun Titulo(texto:String) {
    Text(
        modifier = Modifier
            .padding(10.dp),
        text = texto,
        color = Color.White,
        fontSize = 32.sp,
        fontFamily = FontFamily.Cursive,
        fontWeight = FontWeight.SemiBold
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .padding(start = 30.dp),
        color = ColorP4,
        shape = RoundedCornerShape(topStart = 100f)
    ) {
    }
}