package com.jjmf.chihuancompose.ui.Features.Detalle.Components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.Model.getFecha
import com.jjmf.chihuancompose.Util.invertir
import com.jjmf.chihuancompose.Util.show
import com.jjmf.chihuancompose.ui.Features.Detalle.DetalleViewModel
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ItemHistorial(
    historial: Historial,
    neg:Boolean = false,
    viewModel: DetalleViewModel = hiltViewModel(),
) {

    var anim by remember { mutableStateOf(false) }

    val time = historial.fecha.getFecha()

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = historial.descripcion.toString(),
                    color = Color.Black,
                    modifier = Modifier.weight(2f)
                )
                if (neg){
                    Text(
                        text = "S/" + historial.dinero?.invertir().toString(),
                        color = if ((historial.dinero?.invertir() ?: 0.0) < 0) ColorRed else ColorP2,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }else{
                    Text(
                        text = "S/" + historial.dinero.toString(),
                        color = if ((historial.dinero ?: 0.0) < 0) ColorRed else ColorP2,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                }
                IconButton(
                    onClick = {
                        anim = !anim
                    },
                    modifier = Modifier.size(40.dp)
                ) {
                    val icono = if (anim) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
                    Icon(imageVector = icono, contentDescription = null, tint = ColorP2)
                }
            }

            AnimatedVisibility(visible = anim, modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                    append("Fecha: ")
                                }
                                append("${time.diaNum}/${time.mes}/${time.anio.substring(2)}")
                            },
                            color = Color.Black
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                                    append("Hora:")
                                }
                                append("${time.hora}:${time.min}")
                            },
                            color = Color.Black
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.state = viewModel.state.copy(mantenimiento = true)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = ColorP2)
                    }
                    IconButton(
                        onClick = {
                            viewModel.deleteHistorial(idHistorial = historial.id)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = ColorRed)
                    }
                }
            }
        }
    }

}
