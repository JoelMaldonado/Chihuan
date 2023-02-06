package com.jjmf.chihuancompose.ui.controller

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.jjmf.chihuancompose.ui.Screens.Deudas.DeudasScreen
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
                    navController.navigate(Rutas.Deudas.route)
                },
                toLogin = {
                    navController.navigate(Rutas.Login.route)
                }
            )
        }
        composable(Rutas.Login.route) {
            LoginScreen(
                toMenu = {
                    navController.popBackStack()
                    navController.navigate(Rutas.Deudas.route)
                }
            )
        }
        composable(Rutas.Deudas.route) {
            DeudasScreen(
                toinformacion = {

                }
            )
        }
    }
}