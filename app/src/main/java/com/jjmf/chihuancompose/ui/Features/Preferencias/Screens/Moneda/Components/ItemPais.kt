package com.jjmf.chihuancompose.ui.Features.Preferencias.Screens.Moneda.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jjmf.chihuancompose.Data.Model.Moneda
import com.jjmf.chihuancompose.Data.Model.Pais


@Composable
fun ItemPais(pais: Pais, click: (Moneda) -> Unit) {
    val moneda = if (pais.moneda?.isNotEmpty() == true) pais.moneda.first() else null
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    moneda?.let(click)
                }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = pais.flags.png,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            moneda?.let {
                Column {
                    Text(text = it.code, fontWeight = FontWeight.Medium)
                    Text(text = it.name, fontSize = 14.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = it.symbol, fontWeight = FontWeight.Medium)
            }
        }
        Divider(color = Color.Gray.copy(0.3f))
    }
}
