package com.jjmf.chihuancompose.ui.Features.Reporte

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.ui.theme.ColorP2

@Composable
fun ReporteScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    R.raw.mant
                )
            ).value,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(300.dp)
        )
        Text(text = "En mantenimiento", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }

}