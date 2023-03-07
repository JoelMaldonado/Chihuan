package com.jjmf.chihuancompose.ui.Routes

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Detalle.DetalleScreen
import com.jjmf.chihuancompose.ui.Features.Menu.MenuScreen
import com.jjmf.chihuancompose.ui.Features.Perfil.PerfilScreen
import com.jjmf.chihuancompose.ui.Screens.BienvenidaScreen
import com.jjmf.chihuancompose.ui.Screens.Login.LoginScreen
import com.jjmf.chihuancompose.ui.Screens.SplashScreen

@Composable
fun NavegacionesScreen(
    activity: Activity
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Splash.route) {
        composable(Rutas.Splash.route) {
            SplashScreen(
                toMenu = {
                    navController.popBackStack()
                    navController.navigate(Rutas.Menu.route)
                },
                toLogin = {
                    navController.popBackStack()
                    navController.navigate(Rutas.Login.route)
                },
                toBienvenida = {
                    navController.navigate(Rutas.Bienvenida.route)
                }
            )
        }
        composable(Rutas.Bienvenida.route){
            BienvenidaScreen(
                toLogin = {
                    navController.popBackStack()
                    navController.navigate(Rutas.Login.route)
                }
            )
        }
        composable(Rutas.Login.route) {
            LoginScreen(
                toMenu = {
                    navController.popBackStack()
                    navController.navigate(Rutas.Menu.route)
                }
            )
        }
        composable(Rutas.Menu.route){
            MenuScreen(
                toDetalle = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "deuda",
                        value = it
                    )
                    navController.navigate(Rutas.Detalle.route)
                },
                toPerfil = {
                    navController.navigate(Rutas.Perfil.route)
                },
                finish = {
                    activity.finish()
                }
            )
        }
        composable(Rutas.Perfil.route){
            PerfilScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }
        composable(Rutas.Detalle.route){
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<Deuda>("deuda")
            if (result!=null){
                DetalleScreen(
                    result,
                    back = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
