package com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Moneda

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Moneda.Components.CajaBuscar
import com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Moneda.Components.ItemPais
import com.jjmf.chihuancompose.ui.components.Titulo

@Composable
fun MonedaScreen(
    back: () -> Unit,
    viewModel: MonedaViewModel = hiltViewModel(),
) {


    LaunchedEffect(key1 = Unit) {
        viewModel.getPaises()
    }

    Titulo(
        title = "Moneda",
        iconLeft = Icons.Default.ArrowBack,
        clickLeft = back
    ) {
        CajaBuscar(
            valor = viewModel.filtro,
            newValor = { newValor ->
                viewModel.filtro = newValor
                viewModel.listFiltro = viewModel.list.filter {
                    it.nombre.uppercase().contains(newValor.uppercase())
                }
            }
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.cargando) {
                CircularProgressIndicator(
                    color = Color.Gray
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(viewModel.listFiltro) {
                        ItemPais(
                            pais = it,
                            click = { moneda ->
                                prefs.saveMoneda(moneda)
                                back()
                            }
                        )
                    }
                }
            }
        }
    }
}