package com.jjmf.chihuancompose.ui.Features.Preferencias.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.zxing.BarcodeFormat
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.ui.Features.Preferencias.PreferenciaViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.journeyapps.barcodescanner.BarcodeEncoder


@Composable
fun AlertaQr(
    viewModel: PreferenciaViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = {
            viewModel.state = viewModel.state.copy(alertaQr = false)
        }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.White,
            elevation = 5.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 15.dp)
            ) {
                viewModel.state.usuario?.id?.let { id ->
                    val bar = BarcodeEncoder()
                    val bit = bar.encodeBitmap(
                        id,
                        BarcodeFormat.QR_CODE,
                        720,
                        720
                    )
                    Image(
                        bitmap = bit.asImageBitmap(),
                        contentDescription = null
                    )
                }
                Text(
                    text = viewModel.state.usuario?.nombres.toString() + " " + viewModel.state.usuario?.apellido.toString(),
                    color = ColorP2,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
                Text(
                    text = stringResource(id = R.string.content_qr),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )/*
                IconButton(
                    onClick = {
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.jjmf.chihuancompose")
                        }
                        val shareIntent = Intent.createChooser(intent, null)
                        context.startActivity(shareIntent)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = ColorS1)
                }*/
            }
        }
    }
}