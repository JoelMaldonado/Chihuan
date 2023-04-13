package com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Moneda.Components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.ui.theme.ColorRed


@Composable
fun CajaBuscar(
    valor: String,
    newValor: (String) -> Unit,
) {
    val focus = LocalFocusManager.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray.copy(0.1f))
                .height(35.dp)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray.copy(0.5f),
                modifier = Modifier.size(20.dp)
            )
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = valor,
                    onValueChange = newValor,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    maxLines = 1,
                    textStyle = TextStyle(fontSize = 14.sp)
                )
                if (valor.isEmpty()) {
                    Text(text = "Buscar", color = Color.Gray.copy(0.5f), fontSize = 14.sp)
                }
            }
            if (valor.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = null,
                    tint = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            newValor("")
                        }
                )
            }
        }

        AnimatedVisibility(visible = valor.isNotEmpty()) {
            Text(
                text = "Cancelar",
                color = ColorRed,
                modifier = Modifier.clickable {
                    newValor("")
                    focus.clearFocus()
                },
                fontWeight = FontWeight.Medium
            )
        }
    }
}