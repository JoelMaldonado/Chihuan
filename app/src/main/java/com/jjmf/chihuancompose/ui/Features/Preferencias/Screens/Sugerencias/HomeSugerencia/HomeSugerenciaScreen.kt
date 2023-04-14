package com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Sugerencias.HomeSugerencia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Sugerencias.SinSugerencias

@Composable
fun HomeSugerenciaScreen(
    toAgregarSugerencia:()->Unit
) {

    Column {
        Button(
            onClick = toAgregarSugerencia
            ,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Añadir sugerencia")
        }

        Text(text = "Mi lista de sugerencias")

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray.copy(0.3f)
        )
        SinSugerencias()
    }

}