package com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Sugerencias

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jjmf.chihuancompose.ui.components.Titulo

@Composable
fun SugerenciasScreen(
    back: () -> Unit,
) {

    Titulo(
        title = "Tus sugerencias",
        iconLeft = Icons.Default.ArrowBack,
        clickLeft = back,
        space = 10.dp,
        alignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Añadir sugerencia")
        }

        Text(text = "Mi lista de sugerencias", modifier = Modifier.align(Alignment.Start))

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray.copy(0.3f)
        )

        SinSugerencias()
    }
}

@Composable
fun SinSugerencias() {
    Icon(
        imageVector = Icons.Default.Mail,
        contentDescription = null,
        modifier = Modifier.size(100.dp),
        tint = Color.Gray.copy(0.5f)
    )
    Text(
        text = "Aún no ha presentado ninguna sugerencia",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.Gray.copy(0.5f)
    )
}
