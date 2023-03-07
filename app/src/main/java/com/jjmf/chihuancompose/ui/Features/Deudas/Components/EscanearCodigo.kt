package com.jjmf.chihuancompose.ui.Features.Deudas.Components

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.jjmf.chihuancompose.ui.Features.Deudas.DeudasViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2


@Composable
fun EscanearCodigo(
    activity: Activity,
    viewModel: DeudasViewModel = hiltViewModel(),
) {
    val request =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
            if (result != null) {
                if (result.contents != null) {
                    viewModel.searchId(result.contents)
                }
            }
        }

    val bool = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = bool.value,
                onCheckedChange = {
                    bool.value = it
                    if (!it){
                        viewModel.isQr = false
                        viewModel.titulo = ""
                    }
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = ColorP2,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Text(text = "¿Compartir una Deuda?", color = Color.Black, fontSize = 14.sp)
        }
        AnimatedVisibility(visible = bool.value) {
            Button(
                onClick = {
                    if (viewModel.isQr){
                        viewModel.insertarCompartida()
                    }else{
                        val scan = IntentIntegrator(activity)
                        scan.setPrompt("Centre el codigo QR en el recuadro")
                        request.launch(scan.createScanIntent())
                    }
                }
            ) {
                if (viewModel.isQr) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Registrar", color = Color.White)
                } else {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Escanear QR", color = Color.White)
                }
            }
        }
    }
}