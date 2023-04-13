package com.jjmf.chihuancompose.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun CajaDinero(
    valor: TextFieldValue,
    newValor:(String)->Unit,
    color: Color = ColorP2
) {

    OutlinedTextField(
        value = valor,
        onValueChange = {
            if (esNumero(it.text) || it.text.isEmpty()) newValor(it.text)
        },
        label = { Text(text = "Monto") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        leadingIcon = {
            Text(
                text = "S/.",
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        },
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
