package com.jjmf.chihuancompose.ui.Screens.Deudas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.firebase.Timestamp
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.Util.redondear
import com.jjmf.chihuancompose.ui.Screens.Deudas.Components.AgregarDeudaAlerta
import com.jjmf.chihuancompose.ui.Screens.Menu.Components.Titulo
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorP4

@Composable
fun DeudasScreen(
    viewModel: DeudasViewModel = hiltViewModel(),
    toinformacion: (String) -> Unit,
) {

    val listado = viewModel.state.value

    var alerta by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(ColorP2),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Titulo("¡Hola Joel!")
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 80f),
            color = Color.White
        ) {
            Box(modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                if (listado.isEmpty()) {
                    SinDeuda(Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(listado) {
                            DeudaCard(Modifier, it, viewModel, toInformacion = toinformacion)
                        }
                    }
                }
                BotonAgregarDeuda() {
                    alerta = true
                }

                if (alerta) AgregarDeudaAlerta { deuda, observa ->
                    alerta = false
                    deuda?.let { deu ->
                        val historial = Historial(
                            fecha = Timestamp.now(),
                            dinero = deu.dinero,
                            descripcion = observa
                        )
                        viewModel.insertar(deu, historial)
                    }
                }
            }
        }
    }
}

@Composable
fun SinDeuda(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Sin deudas pendientes",
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pet))
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(150.dp),
            iterations = 100,
        )
    }
}

@Composable
fun BotonAgregarDeuda(click: () -> Unit) {
    FloatingActionButton(onClick = { click() }, backgroundColor = ColorP1) {
        Icon(imageVector = Icons.Default.PostAdd, contentDescription = null, tint = Color.White)
    }
}
