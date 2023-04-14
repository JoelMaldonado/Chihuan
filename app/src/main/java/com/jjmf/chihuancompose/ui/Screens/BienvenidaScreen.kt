package com.jjmf.chihuancompose.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

    val fotos = listOf(
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pager,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(10.dp),

            ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(
                    model = fotos[it],
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = when (pager.currentPage + 1) {
                        1 -> "Gestiona tu dinero"
                        2 -> "Sabras cuanto dinero prestas o debes"
                        3 -> "Comparte Deudas"
                        else -> ""
                    },
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Text(
                    text = when (pager.currentPage + 1) {
                        1 -> "El motivo de la app es para que puedas controlar mejor tu dinero"
                        2 -> "Puedes prestar dinero a tus amigos o familiares y tenerlo todo guardado en la app"
                        3 -> "Puedes compartir deudas con amigos, asi ambos pueden ver en tiempo real el dinero que se deben"
                        else -> ""
                    },
                    textAlign = TextAlign.Center,
                    color = Color.Gray.copy(0.8f)
                )
            }
        }

        HorizontalPagerIndicator(pagerState = pager)


        Button(
            onClick = {
                when (pager.currentPage + 1) {
                    1 -> {
                        coroutine.launch {
                            pager.animateScrollToPage(1)
                        }
                    }
                    2 -> {
                        coroutine.launch {
                            pager.animateScrollToPage(2)
                        }
                    }
                    3 -> {
                        prefs.saveBienvenida(true)
                        toLogin()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(30.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            when (pager.currentPage + 1) {
                1 -> Text(text = "Siguiente")
                2 -> Text(text = "Siguiente")
                3 -> Text(text = "Comenzemos")
            }
        }

    }
}