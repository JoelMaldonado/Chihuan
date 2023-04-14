package com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Sugerencias

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Sugerencias.AgregarSugerencia.AgregarSugerenciaScreen
import com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Sugerencias.HomeSugerencia.HomeSugerenciaScreen
import com.jjmf.chihuancompose.ui.Routes.Rutas
import com.jjmf.chihuancompose.ui.components.Titulo

@Composable
fun SugerenciasScreen(
    back: () -> Unit
) {

    val navController = rememberNavController()

    Titulo(
        title = "Tus sugerencias",
        iconLeft = Icons.Default.ArrowBack,
        clickLeft = back,
        space = 10.dp,
        alignment = Alignment.CenterHorizontally
    ) {
        NavHost(navController = navController, startDestination = Rutas.Preferencia.Sugerencia.Home.route){
            composable(Rutas.Preferencia.Sugerencia.Home.route){
                HomeSugerenciaScreen(
                    toAgregarSugerencia = {
                        navController.navigate(Rutas.Preferencia.Sugerencia.Agregar.route)
                    }
                )
            }
            composable(Rutas.Preferencia.Sugerencia.Agregar.route){
                AgregarSugerenciaScreen(
                    back = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
fun SinSugerencias() {
    Icon(
        imageVector = Icons.Default.Mail,
        contentDescription = null,
        modifier = Modifier.size(100.dp),
        tint = Color.Gray.copy(0.5f)
    )
    Text(
        text = "Aún no ha presentado ninguna sugerencia",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.Gray.copy(0.5f)
    )
}
