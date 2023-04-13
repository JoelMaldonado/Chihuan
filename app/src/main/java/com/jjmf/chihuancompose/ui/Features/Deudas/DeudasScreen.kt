package com.jjmf.chihuancompose.ui.Features.Deudas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.AgregarDeudaAlerta
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.DeudaCardAgregar
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.SinDeuda
import com.jjmf.chihuancompose.ui.components.Titulo
import com.jjmf.chihuancompose.ui.components.card.DeudaCard

@Composable
fun DeudasScreen(
    viewModel: DeudasViewModel = hiltViewModel(),
    toDetalle: (Deuda?) -> Unit,
    toPerfil: () -> Unit,
) {

    if (viewModel.state.alerta) {
        AgregarDeudaAlerta()
    }

    Titulo(
        title = "Deudas",
        iconRight = Icons.Default.Settings,
        clickRight = toPerfil
    ) {
        if (viewModel.state.listado.isEmpty()) {
            SinDeuda()
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.state.listado) { deuda ->
                DeudaCard(
                    deuda = deuda,
                    toDetalle = {
                        toDetalle(deuda)
                    }
                )
            }
            item {
                DeudaCardAgregar(
                    modifier = Modifier.height(120.dp),
                    click = {
                        viewModel.state = viewModel.state.copy(alerta = true)
                    }
                )
            }
        }
    }
}