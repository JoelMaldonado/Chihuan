package com.jjmf.chihuancompose.ui.Features.Detalle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Detalle.Components.AlertaEliminarDeuda
import com.jjmf.chihuancompose.ui.Features.Detalle.Components.AlertaModificarDeuda
import com.jjmf.chihuancompose.ui.Features.Detalle.Components.ItemHistorial
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.SinDeuda
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.TituloAgregarDeuda
import com.jjmf.chihuancompose.ui.theme.ColorOrange
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed
import kotlinx.coroutines.delay

@Composable
fun DetalleScreen(
    deuda: Deuda,
    back: () -> Unit,
    viewModel: DetalleViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.getList(idDeuda = deuda.id ?: "")
    }

    if (viewModel.state.alertaModificar) {
        AlertaModificarDeuda(deuda)
    }

    if (viewModel.state.alertaEliminar) {
        AlertaEliminarDeuda(deuda = deuda, back = back)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TituloAgregarDeuda("Detalles")
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            if (viewModel.state.listado.isEmpty()) {
                item {
                    SinDeuda(texto = "Sin Historial", modifier = Modifier.padding(top = 200.dp))
                }
            }
            items(viewModel.state.listado) {
                ItemHistorial(it, neg = deuda.segundo)
            }
        }

        BannerAdView()

        if (viewModel.state.mantenimiento) {
            Snackbar(
                backgroundColor = ColorOrange,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "En manteminiento", color = Color.White)
                LaunchedEffect(key1 = true) {
                    delay(2500)
                    viewModel.state = viewModel.state.copy(mantenimiento = false)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FloatingActionButton(
                onClick = {
                    viewModel.state = viewModel.state.copy(alertaEliminar = true)
                },
                backgroundColor = ColorRed
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            FloatingActionButton(
                onClick = {
                    viewModel.state = viewModel.state.copy(alertaModificar = true)
                },
                backgroundColor = ColorP2
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }


    }
}

@Composable
fun BannerAdView() {
    AndroidView(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .height(300.dp),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.MEDIUM_RECTANGLE)
                // Add your adUnitID, this is for testing.
                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}