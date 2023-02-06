package com.jjmf.chihuancompose.ui.Screens.Deudas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.ui.Screens.Deudas.Components.AgregarDeudaAlerta
import com.jjmf.chihuancompose.ui.Screens.Menu.Components.Titulo
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.viewModel.DeudasViewModel

@Composable
fun DeudasScreen(
    viewModel: DeudasViewModel = hiltViewModel(),
    toinformacion: (String) -> Unit,
) {


    val user = Firebase.auth.currentUser?.displayName

    val state = viewModel.state

    Column(modifier = Modifier
        .fillMaxSize()
        .background(ColorP2),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Titulo("¡Hola $user!")
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
                if (state.listado.isEmpty()) {
                    SinDeuda(Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.listado) {deuda->
                            DeudaCard(
                                deuda = deuda,
                                toInformacion = toinformacion
                            )
                        }
                    }
                }
                BotonAgregarDeuda() {
                    viewModel.state = state.copy(alerta = true)
                }

                if (state.alerta) {
                    AgregarDeudaAlerta()
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
