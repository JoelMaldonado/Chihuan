package com.jjmf.chihuancompose.ui.Routes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Diario.DiarioScreen
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasScreen
import com.jjmf.chihuancompose.ui.Features.Reporte.ReporteScreen


@Composable
fun NavegacionMenu(nav: NavHostController, modifier: Modifier, toDetalle:(Deuda?)->Unit) {
    NavHost(
        modifier = modifier.fillMaxWidth(), navController = nav, startDestination = Rutas.Deudas.route){
        composable(Rutas.Deudas.route){
            DeudasScreen(
                toDetalle = toDetalle
            )
        }
        composable(Rutas.Diario.route){
            DiarioScreen()
        }

        composable(Rutas.Reporte.route){
            ReporteScreen()
        }
    }
}