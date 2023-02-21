package com.jjmf.chihuancompose.ui.Features.Diario.Components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Features.Diario.DiarioViewModel
import com.jjmf.chihuancompose.ui.theme.ColorRed


@Composable
fun FabGasto(
    viewModel: DiarioViewModel = hiltViewModel(),
) {
    FloatingActionButton(
        onClick = {
            viewModel.state = viewModel.state.copy(alertaGasto = true)
        },
        backgroundColor = ColorRed
    ) {
        Icon(imageVector = Icons.Default.Remove, contentDescription = null, tint = Color.White)
    }
}