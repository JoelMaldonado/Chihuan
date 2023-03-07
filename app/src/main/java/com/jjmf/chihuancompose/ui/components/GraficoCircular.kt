package com.jjmf.chihuancompose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.ui.theme.ColorP1


@Composable
fun GraficoCircular(
    modifier: Modifier,
    indicador: Int = 0,
    color: Color,
    titulo: String,
    descrip: String,
) {
    var valorPermitido by remember { mutableStateOf(100) }
    valorPermitido = if (indicador <= 100) indicador
    else 100

    var indicadorAnimado by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = valorPermitido) {
        indicadorAnimado = valorPermitido.toFloat()
    }

    val percentage = (indicadorAnimado / 100) * 100

    val angulo by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(2000)
    )

    val valorRecibido by animateIntAsState(
        targetValue = valorPermitido,
        animationSpec = tween(2000)
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(400.dp)){
            val componentSize = size / 1.25f
            backgroundGris(
                componentSize = componentSize
            )
            backgroundIndicador(
                sweepAngle = angulo,
                componentSize = componentSize,
                color = color
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$valorRecibido%",
                color = ColorP1,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = titulo,
                color = color,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = descrip,
                color = color,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun DrawScope.backgroundGris(
    componentSize: Size
) {
    drawArc(
        size = componentSize,
        color = Color.Gray.copy(alpha = 0.2f),
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = 40f,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.backgroundIndicador(
    sweepAngle: Float,
    componentSize: Size,
    color: Color
) {
    drawArc(
        size = componentSize,
        color = color,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = 40f,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}