package com.jjmf.chihuancompose.ui.Features.Perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jjmf.chihuancompose.ui.Features.Perfil.Components.AlertaQr
import com.jjmf.chihuancompose.ui.Features.Perfil.Components.ItemPerfil
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorP3

@Composable
fun PerfilScreen(
    back: () -> Unit,
    signOut:()->Unit,
    viewModel: PerfilViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.getUsuario()
    }

    if (viewModel.state.alertaQr) {
        AlertaQr()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP2)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = back
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Mi QR", color = Color.White, fontSize = 12.sp)
            IconButton(
                onClick = {
                    viewModel.state = viewModel.state.copy(alertaQr = true)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.QrCode,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = viewModel.state.usuario?.foto,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(15.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    text = "${viewModel.state.usuario?.nombres} ${viewModel.state.usuario?.apellido}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = viewModel.state.usuario?.correo.toString(),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .padding(start = 30.dp)
                .clip(RoundedCornerShape(topStart = 30.dp))
                .background(ColorP3)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 30.dp))
                .background(Color.White)
                .padding(10.dp)
        ) {
            /*Text(
                text = "Preferencias",
                color = ColorP1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp)
            )
            ItemPerfil(icon = Icons.Default.RestartAlt, texto = "Gastos Concurrentes", click = {

            })
            ItemPerfil(icon = Icons.Default.Bolt, texto = "Gastos Rapidos", click = {

            })
            ItemPerfil(
                icon = Icons.Default.Notifications,
                texto = "Recordatorio de Pagos",
                click = {

                })*/
            ItemPerfil(
                icon = Icons.Default.Logout,
                texto = "Cerrar Sesión",
                click = signOut

            )
        }
    }
}
