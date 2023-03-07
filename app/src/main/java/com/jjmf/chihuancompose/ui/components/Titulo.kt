package com.jjmf.chihuancompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP4
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.Features.Menu.MenuViewModel


@Composable
fun Titulo(
    toPerfil:()->Unit,
    viewModel: MenuViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp),
            text = "¡Hola ${viewModel.state.usuario?.nombres}!",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = toPerfil,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .padding(start = 30.dp),
        color = ColorP4,
        shape = RoundedCornerShape(topStart = 100f)
    ) {
    }
}