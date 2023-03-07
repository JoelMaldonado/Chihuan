package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP1

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeudaCardAgregar(
    modifier: Modifier,
    click:()->Unit
) {
    Card(
        modifier = modifier.padding(10.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
        onClick = click,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = ColorP1
            )
            Text(text = "Agregar Deuda", color = ColorP1, fontWeight = FontWeight.Bold)
        }
    }
}