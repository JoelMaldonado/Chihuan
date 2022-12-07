package com.jjmf.chihuancompose.ui.Screens.Informacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Screens.Informacion.Components.CardHistorial


@Composable
fun InformacionScreen(
    viewModel: InformacionViewModel = hiltViewModel(),
    idDeuda: String,
    back: () -> Unit,
) {
    viewModel.getList(idDeuda)
    val state = viewModel.state.value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Historial",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold)
        Button(
            onClick = {
                viewModel.delete(idDeuda)
                back()
            }
        ) {
            Text(text = "ELiminar")
        }
        LazyColumn() {
            items(state) {
                CardHistorial(it)
            }
        }
    }
}
