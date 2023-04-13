package com.jjmf.chihuancompose.ui.Features.Preferencias.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.ui.theme.ColorP1

@Composable
fun ItemPerfil(
    icon: ImageVector,
    texto: String,
    content:String = "",
    descrip:String? = null,
    click: () -> Unit,
    isClick:Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isClick) { click() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        Text(
            text = texto,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        if (descrip != null){
            Text(text = descrip)
        }else{
            Text(text = content, fontSize = 14.sp)
            Icon(
                imageVector = Icons.Default.ArrowRight,
                contentDescription = null,
                tint = ColorP1
            )
        }
    }
}