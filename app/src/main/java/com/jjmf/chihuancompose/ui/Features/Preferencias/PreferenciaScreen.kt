package com.jjmf.chihuancompose.ui.Features.Preferencias

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.compose.AsyncImage
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.Util.show
import com.jjmf.chihuancompose.ui.Features.Preferencias.Components.AlertaQr
import com.jjmf.chihuancompose.ui.Features.Preferencias.Components.ElegirIdiomaModal
import com.jjmf.chihuancompose.ui.Features.Preferencias.Components.ItemPerfil
import com.jjmf.chihuancompose.ui.components.Titulo
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorP3
import com.jjmf.chihuancompose.ui.theme.ColorRed
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PreferenciaScreen(
    back: () -> Unit,
    signOut: () -> Unit,
    toMoneda: () -> Unit,
    viewModel: PreferenciaViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.getUsuario()
    }

    val context = LocalContext.current

    if (viewModel.state.alertaQr) {
        AlertaQr()
    }

    val bottomState = rememberBottomSheetScaffoldState()
    val coroutine = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomState,
        sheetContent = {
            ElegirIdiomaModal(
                click = {
                    coroutine.launch {
                        bottomState.bottomSheetState.collapse()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {
        Titulo(
            title = "Prefencias",
            iconLeft = Icons.Default.ArrowBack,
            clickLeft = back,
            space = 10.dp
        ) {

            val user = prefs.getUser()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = user?.foto,
                    contentDescription = null,
                    error = painterResource(id = R.drawable.perfil_default),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = user?.nombres.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(text = user?.correo.toString(), fontSize = 14.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        viewModel.state = viewModel.state.copy(alertaQr = true)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCode,
                        contentDescription = null,
                        tint = ColorP2
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                color = Color.Gray.copy(0.1f)
            )

            ItemPerfil(
                icon = Icons.Default.Money,
                texto = "Moneda",
                click = toMoneda,
                content = prefs.getMoneda()?.code ?: "USD"
            )

            ItemPerfil(
                icon = Icons.Default.Language,
                texto = "Idioma",
                click = {
                    coroutine.launch {
                        bottomState.bottomSheetState.expand()
                    }
                },
                content = "Español"
            )
            ItemPerfil(
                icon = Icons.Default.SettingsSuggest,
                texto = "Sugerencia",
                click = {}
            )
            ItemPerfil(
                icon = Icons.Default.Star,
                texto = "Califica esta aplicación",
                click = {
                    val uri =
                        Uri.parse("https://play.google.com/store/apps/details?id=com.jjmf.chihuancompose")
                    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            )
            ItemPerfil(
                icon = Icons.Default.Person,
                texto = "Contactar con el desarrollador",
                click = {
                    val uri = Uri.parse("https://api.whatsapp.com/send?phone=51936416623")
                    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            )
            ItemPerfil(
                icon = Icons.Default.ConfirmationNumber,
                texto = "Version",
                descrip = "1.0.0",
                click = {
                    context.show("Te encuentras en la versión 1.0.0 de la app chihuan")
                }
            )
            ItemPerfil(
                icon = Icons.Default.Logout,
                texto = "Cerrar Sesión",
                click = {
                    cerrarSesion(
                        context = context,
                        click = signOut
                    )
                }
            )
        }
    }

}

fun cerrarSesion(context: Context, click: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE).apply {
        titleText = "¿Seguro que desea salir?"
        confirmButtonBackgroundColor = Color.Transparent.hashCode()
        cancelButtonBackgroundColor = Color.Transparent.hashCode()
        confirmButtonTextColor = ColorP3.hashCode()
        cancelButtonTextColor = ColorRed.hashCode()
        setCancelButton("No") {
            dismissWithAnimation()
        }
        setConfirmButton("Si") {
            dismissWithAnimation()
            click()
        }
        show()
    }
}
