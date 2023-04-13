package com.jjmf.chihuancompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.chihuancompose.ui.theme.ColorP1
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun Titulo(
    title: String,
    iconLeft: ImageVector? = null,
    clickLeft: (() -> Unit)? = null,
    iconRight: ImageVector? = null,
    clickRight: (() -> Unit)? = null,
    space:Dp = 0.dp,
    content: @Composable ColumnScope.()->Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP2),
        verticalArrangement = Arrangement.spacedBy(space)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            iconLeft?.let {
                IconButton(
                    onClick = clickLeft!!,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp),
                text = title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            iconRight?.let {
                IconButton(
                    onClick = clickRight!!,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 30.dp))
                .background(if (isSystemInDarkTheme()) ColorP1 else Color.White)
                .padding(10.dp),
            content = content
        )
    }
}