package com.jjmf.chihuancompose.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BienvenidaScreen(
    toLogin: () -> Unit,
) {

    val pager = rememberPagerState(pageCount = 3)
    val coroutine = rememberCoroutineScope()

    val foto = when (pager.currentPage + 1) {
        1 -> R.drawable.img_1
        2 -> R.drawable.img_2
        3 -> R.drawable.img_3
        else -> null
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pager,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(10.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = foto,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(300.dp)
                )
            }
        }
        HorizontalPagerIndicator(pagerState = pager)

        Text(
            text = when (pager.currentPage + 1) {
                1 -> "Chihuan te ayudara a controlar tus gastos"
                2 -> "Sabras cuanto dinero prestas o debes"
                3 -> "Comienza a administrar tu dinero"
                else -> ""
            },
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Button(
            onClick = {
                when (pager.currentPage + 1) {
                    1 -> {
                        coroutine.launch {
                            pager.scrollToPage(1)
                        }
                    }
                    2 -> {
                        coroutine.launch {
                            pager.scrollToPage(2)
                        }
                    }
                    3 -> {
                        prefs.saveBienvenida(true)
                        toLogin()
                    }
                }
            }
        ) {
            when (pager.currentPage + 1) {
                1 -> Text(text = "Siguiente")
                2 -> Text(text = "Siguiente")
                3 -> Text(text = "Entendido")
            }
        }

    }
}