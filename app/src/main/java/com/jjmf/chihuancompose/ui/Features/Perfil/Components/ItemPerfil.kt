package com.jjmf.chihuancompose.ui.Features.Perfil.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun ItemPerfil(
    icon: ImageVector,
    texto: String,
    click:()->Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = ColorP2)
            Text(
                text = texto,
                fontWeight = FontWeight.SemiBold,
                color = ColorP1,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = click) {
                Icon(
                    imageVector = Icons.Default.ArrowRight,
                    contentDescription = null,
                    tint = ColorP1
                )
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray.copy(0.5f))
    }
}