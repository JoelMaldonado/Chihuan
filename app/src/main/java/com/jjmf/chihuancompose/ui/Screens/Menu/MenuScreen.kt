package com.jjmf.chihuancompose.ui.Screens.Menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Today
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jjmf.chihuancompose.ui.Screens.Ajuste.AjusteScreen
import com.jjmf.chihuancompose.ui.Screens.Deudas.DeudasScreen
import com.jjmf.chihuancompose.ui.Screens.Diario.DiarioScreen
import com.jjmf.chihuancompose.ui.Screens.Dobles.DoblesScreen
import com.jjmf.chihuancompose.ui.Screens.Informacion.InformacionScreen
import com.jjmf.chihuancompose.ui.theme.ColorS1

@Composable
fun Menu(
    viewModel: MenuViewModel = hiltViewModel(),
    toMovimiento:()->Unit
) {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column() {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                    NavHost(navController = navController, startDestination = "deuda") {
                        composable("deuda") {
                            DeudasScreen(
                                toinformacion = {idDeuda->
                                    navController.navigate("informacion/$idDeuda")
                                }
                            )
                        }
                        composable("compartidas") {
                            DoblesScreen()
                        }
                        composable("diario") {
                            DiarioScreen(
                                toMovimiento = toMovimiento
                            )
                        }
                        composable("ajuste") {
                            AjusteScreen()
                        }
                        composable("informacion/{idDeuda}",
                            arguments = listOf(navArgument("idDeuda") {
                                type = NavType.StringType
                            })) {
                            InformacionScreen(idDeuda = it.arguments?.getString("idDeuda")!!){
                                navController.popBackStack()
                            }
                        }
                    }
                }
                BottomBar(navController = navController)
            }
        }
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Money, "") },
            label = { Text(text = "Deudas") },
            selected = selectedIndex.value == 0,
            onClick = {
                selectedIndex.value = 0
                navController.backQueue.clear()
                navController.navigate(route = "deuda")
            },
            selectedContentColor = ColorS1,
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.People, "") },
            label = { Text(text = "Usuarios") },
            selected = selectedIndex.value == 1,
            onClick = {
                selectedIndex.value = 1
                navController.backQueue.clear()
                navController.navigate(route = "compartidas")
            },
            selectedContentColor = ColorS1,
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Today, "") },
            label = { Text(text = "Diario") },
            selected = selectedIndex.value == 2,
            onClick = {
                selectedIndex.value = 2
                navController.backQueue.clear()
                navController.navigate(route = "diario")
            },
            selectedContentColor = ColorS1,
            unselectedContentColor = Color.White
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Settings, "") },
            label = { Text(text = "Ajustes") },
            selected = selectedIndex.value == 3,
            onClick = {
                selectedIndex.value = 3
                navController.backQueue.clear()
                navController.navigate(route = "ajuste")
            },
            selectedContentColor = ColorS1,
            unselectedContentColor = Color.White
        )
    }
}