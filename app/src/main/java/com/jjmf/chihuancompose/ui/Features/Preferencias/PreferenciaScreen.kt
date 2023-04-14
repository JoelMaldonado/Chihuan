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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.compose.AsyncImage
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.BuildConfig
import com.jjmf.chihuancompose.R
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
    refresh: () -> Unit,
    signOut: () -> Unit,
    toMoneda: () -> Unit,
    toSugerencia: () -> Unit,
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
                    refresh()
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
            title = stringResource(R.string.title_preferencias),
            iconLeft = Icons.Default.ArrowBack,
            clickLeft = back,
            space = 5.dp
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
                    .padding(vertical = 10.dp),
                color = Color.Gray.copy(0.1f)
            )

            ItemPerfil(
                icon = Icons.Default.Money,
                texto = stringResource(R.string.pref_moneda),
                click = toMoneda,
                content = prefs.getMoneda()?.code ?: "USD"
            )

            ItemPerfil(
                icon = Icons.Default.Language,
                texto = stringResource(R.string.pref_idioma),
                click = {
                    coroutine.launch {
                        bottomState.bottomSheetState.expand()
                    }
                },
                content = if (prefs.getIdioma() == "es") stringResource(R.string.espaniol)
                else stringResource(R.string.ingles)
            )
            ItemPerfil(
                icon = Icons.Default.SettingsSuggest,
                texto = stringResource(R.string.pref_sugerencia),
                click = toSugerencia
            )
            ItemPerfil(
                icon = Icons.Default.Star,
                texto = stringResource(R.string.pref_califica),
                click = {
                    val uri =
                        Uri.parse("https://play.google.com/store/apps/details?id=com.jjmf.chihuancompose")
                    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            )
            ItemPerfil(
                icon = Icons.Default.Person,
                texto = stringResource(R.string.pref_contactar),
                click = {
                    val uri = Uri.parse("https://api.whatsapp.com/send?phone=51936416623")
                    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            )
            ItemPerfil(
                icon = Icons.Default.ConfirmationNumber,
                texto = stringResource(R.string.pref_version),
                descrip = BuildConfig.VERSION_NAME,
                click = {},
                isClick = false
            )
            ItemPerfil(
                icon = Icons.Default.Logout,
                texto = stringResource(R.string.pref_cerrar_sesion),
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
