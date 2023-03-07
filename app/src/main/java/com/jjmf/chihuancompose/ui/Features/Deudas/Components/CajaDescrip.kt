package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun CajaDescrip(
    valor:String,
    newValor:(String)->Unit,
    color: Color = ColorP2
) {
    Column{
        OutlinedTextField(
            value = valor,
            onValueChange = newValor,
            label = { Text(text = "Descripción") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color,
                cursorColor = color,
                focusedLabelColor = color,
                unfocusedBorderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray
            ),
            textStyle = TextStyle(color = Color.Black)
        )
    }
}