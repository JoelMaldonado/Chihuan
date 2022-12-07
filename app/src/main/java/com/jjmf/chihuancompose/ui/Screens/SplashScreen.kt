package com.jjmf.chihuancompose.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.jjmf.chihuancompose.ui.theme.ColorP2
import kotlinx.coroutines.*

@Composable
fun SplashScreen(
    toLogin: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(ColorP2))
    LaunchedEffect(key1 = "sd") {
        tiempo() {
            toLogin()
        }
    }
}

fun tiempo(evento: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        delay(200)
        withContext(Dispatchers.Main) {
            evento()
        }
    }
}
