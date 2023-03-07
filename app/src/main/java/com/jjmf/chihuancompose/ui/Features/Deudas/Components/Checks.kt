package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP1


@Composable
fun Checks(
    bool: Boolean,
    newValor: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RadioButton(
            selected = bool,
            onClick = { newValor(true) },
            colors = RadioButtonDefaults.colors(
                selectedColor = ColorP1,
                unselectedColor = Color.Gray
            )
        )
        Text(text = "Prestamo", color = Color.Black)
        Spacer(modifier = Modifier.width(5.dp))
        RadioButton(
            selected = !bool,
            onClick = {
                newValor(false)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = ColorP1,
                unselectedColor = Color.Gray
            )
        )
        Text(text = "Recibir", color = Color.Black)
    }
}