package com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Sugerencias.AgregarSugerencia

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.ui.theme.ColorP2
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun AgregarSugerenciaScreen(
    back: () -> Unit,
) {
    val bool = remember { mutableStateOf(false) }


    val descrip = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }

    val usuario = prefs.getUser()
    LaunchedEffect(key1 = Unit) {
        usuario?.correo?.let {
            correo.value = it
        }
    }

    val bottomState = rememberBottomSheetScaffoldState()
    val coroutine = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomState,
        sheetContent = {
            ObtenerImagenModal()
        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Transparent,
        backgroundColor = Color.Transparent,
        drawerBackgroundColor = Color.Transparent
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {

            val color = if (bool.value) ColorP2 else Color.Transparent
            val color2 = if (!bool.value) ColorP2 else Color.Transparent
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray.copy(0.5f))
            ) {
                Text(
                    text = "Reportar error",
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color)
                        .clickable { bool.value = true }
                        .padding(vertical = 5.dp, horizontal = 15.dp),
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Nueva función",
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color2)
                        .clickable { bool.value = false }
                        .padding(vertical = 5.dp, horizontal = 15.dp),
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray.copy(0.3f))
                    .padding(10.dp)
            ) {
                if (descrip.value.isEmpty()) {
                    Text(
                        text = "Detalla el error o sugerencia para que nos puedas ayudar a mejorar esta app",
                        color = Color.Gray.copy(0.8f)
                    )
                }
                BasicTextField(
                    value = descrip.value,
                    onValueChange = { descrip.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    textStyle = TextStyle(textAlign = TextAlign.Start)
                )
            }

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                item {
                    AddPhoto(
                        click = {
                            coroutine.launch {
                                bottomState.bottomSheetState.expand()
                            }
                        }
                    )
                }
            }

            OutlinedTextField(
                value = correo.value,
                onValueChange = { correo.value = it },
                placeholder = {
                    Text(text = "Complete su correo electronico")
                },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                singleLine = true
            )
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Enviar")
            }
        }
    }

}

@Composable
fun ObtenerImagenModal() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Tomar una foto")
                Divider()
                Text(text = "Escoger una imagen")
            }
        }

        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Cancelar")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddPhoto(click: () -> Unit) {
    Card(
        modifier = Modifier
            .size(70.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = click
    ) {
        Icon(
            imageVector = Icons.Default.AddPhotoAlternate,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            tint = Color.Gray.copy(0.5f)
        )
    }
}
