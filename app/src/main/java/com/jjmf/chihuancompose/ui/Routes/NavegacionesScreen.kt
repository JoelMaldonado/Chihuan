package com.jjmf.chihuancompose.ui.Routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Detalle.DetalleScreen
import com.jjmf.chihuancompose.ui.Features.Menu.MenuScreen
import com.jjmf.chihuancompose.ui.Screens.Login.LoginScreen
import com.jjmf.chihuancompose.ui.Screens.SplashScreen
import com.jjmf.chihuancompose.ui.viewModel.LoginViewModel

@Composable
fun NavegacionesScreen(
    viewModel: LoginViewModel = hiltViewModel()
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
