package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jjmf.chihuancompose.R


@Composable
fun SinDeuda(
    texto:String = "Sin deudas pendientes",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = texto,
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pet))
        Spacer(modifier = Modifier.height(10.dp))
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(150.dp),
            iterations = 100,
        )
    }
}