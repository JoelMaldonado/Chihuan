package com.jjmf.chihuancompose.ui.Features.Menu

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Routes.NavegacionMenu
import com.jjmf.chihuancompose.ui.Routes.Rutas
import com.jjmf.chihuancompose.ui.components.Titulo
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorP3

@Composable
fun MenuScreen(
    toDetalle: (Deuda?) -> Unit,
    toPerfil:()->Unit,
    finish:()->Unit,
    viewModel: MenuViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        viewModel.getUsuario()
    }
    val navMenuController = rememberNavController()

    val context = LocalContext.current
    BackHandler() {
        SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE).apply {
            titleText = "¿Estas seguro de salir?"
            confirmButtonBackgroundColor = ColorP2.hashCode()
            setConfirmButton("Salir"){
                finish()
                dismissWithAnimation()
            }
            setCancelButton("Cancelar"){
                dismissWithAnimation()
            }
            show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP2),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Titulo(
            toPerfil = toPerfil
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 80f),
            color = Color.White
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                NavegacionMenu(
                    nav = navMenuController,
                    modifier = Modifier.weight(1f),
                    toDetalle = toDetalle
                )
                MenuBottom(navMenuController)
            }
        }
    }
}

@Composable
fun MenuBottom(nav: NavHostController) {
    val isSelected = remember { mutableStateOf(1) }
    Card(
        modifier = Modifier
            .width(300.dp)
            .padding(bottom = 20.dp),
        shape = RoundedCornerShape(50.dp),
        elevation = 5.dp,
        backgroundColor = ColorP3,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ItemMenu(
                icon = Icons.Default.Money,
                isSelected = isSelected,
                code = 1,
                click = {
                    nav.backQueue.clear()
                    nav.navigate(Rutas.Deudas.route)
                }
            )
            ItemMenu(
                icon = Icons.Default.CalendarToday,
                isSelected = isSelected,
                code = 2,
                click = {
                    nav.backQueue.clear()
                    nav.navigate(Rutas.Diario.route)
                }
            )
            ItemMenu(
                icon = Icons.Default.History,
                isSelected = isSelected,
                code = 3,
                click = {

                    nav.backQueue.clear()
                    nav.navigate(Rutas.Reporte.route)
                }
            )
        }
    }
}

@Composable
fun ItemMenu(
    icon: ImageVector,
    isSelected: MutableState<Int>,
    code: Int,
    click: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                isSelected.value = code
                click()
            }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected.value == code) Color.White else Color.Black
            )
        }
        AnimatedVisibility(visible = isSelected.value == code) {
            Divider(thickness = 2.dp, color = Color.White, modifier = Modifier.width(30.dp))
        }
    }
}
