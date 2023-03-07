package com.jjmf.chihuancompose.ui.Features.Reporte

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Features.Reporte.Components.CardReporte
import com.jjmf.chihuancompose.ui.components.GraficoCircular
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed
import kotlin.math.absoluteValue

@Composable
fun ReporteScreen(
    viewModel: ReporteViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        viewModel.get()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = 5.dp,
            backgroundColor = Color.White
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val ingresos = getNumber(
                        viewModel.state.ingresos.absoluteValue,
                        viewModel.state.gastos.absoluteValue
                    )
                    val gastos = getNumber(
                        viewModel.state.gastos.absoluteValue,
                        viewModel.state.ingresos.absoluteValue
                    )
                    GraficoCircular(
                        modifier = Modifier.size(180.dp),
                        indicador = ingresos.toInt(),
                        color = ColorP2,
                        titulo = "Ingresos",
                        descrip = "S/${viewModel.state.ingresos}"
                    )
                    GraficoCircular(
                        modifier = Modifier.size(180.dp),
                        indicador = gastos.toInt(),
                        color = ColorRed,
                        titulo = "Gastos",
                        descrip = "S/${viewModel.state.gastos}"
                    )
                }
                Text(
                    text = "Billetera: ${viewModel.state.total}",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 5.dp),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            items(viewModel.state.listado.sortedByDescending { it.time }) {
                CardReporte(it)
                Divider()
            }
        }
    }
}

private fun getNumber(a: Double, b: Double) = (a / (a + b)) * 100
