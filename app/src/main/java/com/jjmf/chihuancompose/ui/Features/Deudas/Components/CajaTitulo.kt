package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun CajaTitulo(
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    OutlinedTextField(
        value = viewModel.titulo,
        onValueChange = { viewModel.titulo = it },
        label = { Text(text = "Titulo") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = ColorP2,
            cursorColor = ColorP2,
            focusedLabelColor = ColorP2
        )
    )
}
