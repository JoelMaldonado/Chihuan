package com.jjmf.chihuancompose.ui.Routes

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Detalle.DetalleScreen
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasScreen
import com.jjmf.chihuancompose.ui.Features.Preferencias.PreferenciaScreen
import com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Moneda.MonedaScreen
import com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Sugerencias.SugerenciasScreen
import com.jjmf.chihuancompose.ui.Features.Registro.RegistroScreen
import com.jjmf.chihuancompose.ui.Screens.BienvenidaScreen
import com.jjmf.chihuancompose.ui.Screens.Login.LoginScreen
import com.jjmf.chihuancompose.ui.Screens.SplashScreen

@Composable
fun NavegacionesScreen(
    activity: Activity,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Splash.route) {
        composable(Rutas.Splash.route) {
            SplashScreen(
                toMenu = {
                    navController.popBackStack()
                    navController.navigate(Rutas.Deudas.route)
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
        composable(Rutas.Bienvenida.route) {
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
                    navController.navigate(Rutas.Deudas.route)
                },
                toRegistro = {
                    navController.navigate(Rutas.Registro.route)
                }
            )
        }
        composable(Rutas.Registro.route) {
            RegistroScreen(
                toMenu = {
                    navController.popBackStack()
                    navController.navigate(Rutas.Deudas.route)
                }
            )
        }
        composable(Rutas.Deudas.route) {
            DeudasScreen(
                toDetalle = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "deuda",
                        value = it
                    )
                    navController.navigate(Rutas.Detalle.route)
                },
                toPerfil = {
                    navController.navigate(Rutas.Preferencia.route)
                }
            )
        }
        composable(Rutas.Preferencia.route) {
            PreferenciaScreen(
                back = {
                    navController.popBackStack()
                },
                refresh = {
                    navController.popBackStack()
                    navController.navigate(Rutas.Preferencia.route)
                },
                signOut = {
                    navController.backQueue.clear()
                    navController.popBackStack()
                    navController.navigate(Rutas.Login.route)
                    prefs.clearUser()
                },
                toMoneda = {
                    navController.navigate(Rutas.Preferencia.Moneda.route)
                },
                toSugerencia = {
                    navController.navigate(Rutas.Preferencia.Sugerencia.route)
                }
            )
        }

        composable(Rutas.Preferencia.Moneda.route) {
            MonedaScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }
        composable(Rutas.Preferencia.Sugerencia.route) {
            SugerenciasScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.Detalle.route) {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<Deuda>("deuda")
            if (result != null) {
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
