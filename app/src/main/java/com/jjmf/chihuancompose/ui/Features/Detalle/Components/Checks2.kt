package com.jjmf.chihuancompose.ui.Features.Detalle.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Features.Detalle.DetalleViewModel
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP1


@Composable
fun Checks2(
    viewModel: DetalleViewModel = hiltViewModel(),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RadioButton(
            selected = viewModel.bool,
            onClick = { viewModel.bool = true },
            colors = RadioButtonDefaults.colors(
                selectedColor = ColorP1
            )
        )
        Text(text = "Recibir")
        Spacer(modifier = Modifier.width(5.dp))
        RadioButton(
            selected = !viewModel.bool,
            onClick = {
                viewModel.bool = false
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = ColorP1
            )
        )
        Text(text = "Pagar")
    }
}