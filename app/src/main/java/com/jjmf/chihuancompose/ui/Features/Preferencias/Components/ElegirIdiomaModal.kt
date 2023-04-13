package com.jjmf.chihuancompose.ui.Features.Preferencias.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP3

@Composable
fun ElegirIdiomaModal(
    click:()->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorP3)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selecciona tu idioma",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Row(
            modifier = Modifier.fillMaxWidth().clickable { click() }.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Español", fontWeight = FontWeight.Medium)
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = ColorP1)
        }
        Row(
            modifier = Modifier.fillMaxWidth().clickable { click() }.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Ingles", fontWeight = FontWeight.Medium)
        }

    }
}