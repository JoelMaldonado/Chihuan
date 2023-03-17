package com.jjmf.chihuancompose.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.ui.theme.ColorP2
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    toLogin: () -> Unit,
    toMenu: () -> Unit,
    toBienvenida: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        if (!prefs.getBienvenida()){
            toBienvenida()
        }else if (prefs.getUser() != null){
            delay(2000)
            toMenu()
        }else {
            delay(2000)
            toLogin()
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
}