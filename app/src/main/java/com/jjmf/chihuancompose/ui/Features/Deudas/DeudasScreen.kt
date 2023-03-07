package com.jjmf.chihuancompose.ui.Features.Deudas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.AgregarDeudaAlerta
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.DeudaCardAgregar
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.SinDeuda
import com.jjmf.chihuancompose.ui.components.card.DeudaCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeudasScreen(
    viewModel: DeudasViewModel = hiltViewModel(),
    toDetalle: (Deuda?) -> Unit,
) {


    if (viewModel.state.alerta) {
        AgregarDeudaAlerta()
    }

    Box(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (viewModel.state.listado.isEmpty()) {
            SinDeuda()
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                DeudaCardAgregar(
                    modifier = Modifier.height(120.dp),
                    click = {
                        viewModel.state = viewModel.state.copy(alerta = true)
                    }
                )
            }
            items(viewModel.state.listado) { deuda ->
                DeudaCard(
                    deuda = deuda,
                    toDetalle = {
                        toDetalle(deuda)
                    },
                    modifier = Modifier.height(120.dp)
                )
            }
        }

    }
}
