package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Util.esNumero
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun BotonInsertDeuda(
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    val bool = (if (esNumero(viewModel.monto.text)) viewModel.monto.text.toDouble() > 0 else false) && viewModel.titulo.isNotEmpty()
    Button(
        onClick = {
            viewModel.insertar()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ColorP2,
            contentColor = Color.White,
            disabledBackgroundColor = Color.Gray.copy(0.5f)
        ),
        enabled = bool
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = if (bool) Color.White else Color.Gray.copy(0.5f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Aceptar", color = if (bool) Color.White else Color.Gray.copy(0.5f))
    }
}
