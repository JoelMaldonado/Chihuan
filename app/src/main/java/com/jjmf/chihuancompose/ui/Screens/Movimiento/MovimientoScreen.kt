package com.jjmf.chihuancompose.ui.Screens.Movimiento

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.MusicVideo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorS1

@Composable
fun MovimientoScreen() {

    var tabIndex by remember { mutableStateOf(0) }

    val tabData = listOf(
        TabItem(nombre = "Gastos", icon = Icons.Default.MusicVideo),
        TabItem(nombre = "Ingresos", icon = Icons.Default.Apps)
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabData.forEachIndexed { index, tabData ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = tabData.nombre) },
                    icon = { Icon(tabData.icon, contentDescription = tabData.nombre) }
                )
            }
        }

        when(tabIndex){
            0-> GastoScreen(Modifier.weight(1f))
            1-> IngresoScreen(Modifier.weight(1f))
        }
    }
}

@Composable
fun IngresoScreen(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(ColorP2), contentAlignment = Alignment.Center){
        Text(text = "Ingresos")
    }
}

@Composable
fun GastoScreen(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(ColorS1), contentAlignment = Alignment.Center){
        Text(text = "Gastos")
    }
}

data class TabItem(
    val nombre:String,
    val icon: ImageVector
)