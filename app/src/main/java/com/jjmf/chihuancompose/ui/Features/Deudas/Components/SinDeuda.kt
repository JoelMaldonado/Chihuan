package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
fun SinDeuda() {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Sin deudas pendientes",
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