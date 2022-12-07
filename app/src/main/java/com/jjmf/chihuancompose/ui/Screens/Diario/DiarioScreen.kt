package com.jjmf.chihuancompose.ui.Screens.Diario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Screens.Diario.Components.AlertaDiario
import com.jjmf.chihuancompose.ui.Screens.Diario.Components.CardDiario
import com.jjmf.chihuancompose.ui.Screens.Menu.Components.Titulo
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun DiarioScreen(
    toMovimiento: ()->Unit,
    viewModel: DiarioViewModel = hiltViewModel()
) {
    val listDiario = viewModel.state.value
    var alert by remember { mutableStateOf(false) }

    if (alert) AlertaDiario{
        alert = false
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(ColorP2),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Titulo("¡Organiza tu dinero!")
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 80f),
            color = Color.White
        ) {
            Box(modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                if (listDiario.isEmpty()){
                    Text(text = "Sin Items", modifier = Modifier.align(Alignment.Center))
                }else{
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(listDiario) {
                            CardDiario(it)
                        }
                    }
                }
                BotonAgregarDiario {
                    toMovimiento()
                    //alert = true
                }
            }
        }
    }
}

@Composable
fun BotonAgregarDiario(click: () -> Unit) {
    FloatingActionButton(
        onClick = { click() },
        backgroundColor = ColorP1
    ) {
        Icon(imageVector = Icons.Default.PostAdd, contentDescription = null, tint = Color.White)
    }
}
