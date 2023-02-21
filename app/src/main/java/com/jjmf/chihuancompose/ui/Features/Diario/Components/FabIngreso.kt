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
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun FabIngreso(
    viewModel: DiarioViewModel = hiltViewModel(),
) {
    FloatingActionButton(
        onClick = {
            viewModel.state = viewModel.state.copy(alertaIngreso = true)
        },
        backgroundColor = ColorP2
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
    }
}
