package com.jjmf.chihuancompose.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.firebase.auth.FirebaseAuth
import com.jjmf.chihuancompose.Application.BaseApp
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.ui.Routes.Rutas
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.viewModel.LoginViewModel
import kotlinx.coroutines.*

@Composable
fun SplashScreen(
    toLogin: () -> Unit,
    toMenu: () -> Unit,
    toBienvenida: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {


    if (!BaseApp.prefs.getBienvenida()) {
        LaunchedEffect(key1 = true) {
            toBienvenida()
        }
    }else {

        val state = viewModel.state

        if (state.usuario != null) {
            LaunchedEffect(key1 = true) {
                delay(1000)
                toMenu()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorP2),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(
                        R.raw.splash
                    )
                ).value, iterations = LottieConstants.IterateForever
            )
        }

        val auth = FirebaseAuth.getInstance()

        val context = LocalContext.current
        if (!state.mensajeError.isNullOrEmpty()) {
            LaunchedEffect(key1 = state.mensajeError) {
                Toast.makeText(context, state.mensajeError, Toast.LENGTH_SHORT).show()
                auth.currentUser?.delete()
                toLogin()
            }
        }

        LaunchedEffect(key1 = true) {
            delay(1000)
            if (auth.currentUser != null) {
                viewModel.getId(auth.currentUser!!.email!!)
            } else {
                toLogin()
            }
        }
    }
}