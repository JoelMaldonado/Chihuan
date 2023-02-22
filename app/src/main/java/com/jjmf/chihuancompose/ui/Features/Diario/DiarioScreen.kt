package com.jjmf.chihuancompose.ui.Features.Diario

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.ui.Features.Diario.Components.*
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed

@Composable
fun DiarioScreen(
    viewModel: DiarioViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        viewModel.getList()
    }

    if (viewModel.state.alertaGasto) {
        viewModel.reset()
        AlertaGasto()
    }

    if (viewModel.state.alertaIngreso) {
        viewModel.reset()
        AlertaIngreso()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ){
            items(viewModel.state.listado){
                ItemDiario(it)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FabIngreso()
            if (viewModel.state.contador<0){
                Text(text = "Gastos: S/${viewModel.state.contador}", fontWeight = FontWeight.SemiBold, color = ColorRed)
            }else{
                Text(text = "Ingresos: S/${viewModel.state.contador}", fontWeight = FontWeight.SemiBold, color = ColorP2)
            }
            FabGasto()
        }
    }
}
