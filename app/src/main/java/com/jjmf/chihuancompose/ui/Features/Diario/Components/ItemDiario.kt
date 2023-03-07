package com.jjmf.chihuancompose.ui.Features.Diario.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.R
import com.jjmf.chihuancompose.ui.Features.Diario.DiarioViewModel
import com.jjmf.chihuancompose.ui.theme.ColorOrange
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.theme.ColorRed

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemDiario(
    diario: Diario,
    viewModel: DiarioViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        backgroundColor = Color.White,
        onClick = {
            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
                titleText = "Eliminar registro"
                confirmButtonBackgroundColor = ColorP2.hashCode()
                cancelButtonBackgroundColor = ColorOrange.hashCode()
                setConfirmButton("Eliminar") {
                    dismissWithAnimation()
                    viewModel.eliminarDiario(diario.id)
                }
                setCancelButton("Cancelar") {
                    dismissWithAnimation()
                }
                show()
            }
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val pos = ((diario.monto) ?: 0.0) < 0

            val icono = if (pos) {
                painterResource(id = R.drawable.ic_dar)
            } else painterResource(id = R.drawable.ic_recibir)
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (pos) ColorRed else ColorP2)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = icono,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
            val time = diario.getFecha(diario.time!!)

            Text(
                text = diario.descrip.toString(),
                color = Color.Black,
                modifier = Modifier.weight(2f)
            )
            val apm = if (time.hora.toInt() < 12) "am" else "pm"
            Text(
                text = "${time.hora}:${time.min} $apm",
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "S/" + diario.monto.toString(),
                color = if ((diario.monto ?: 0.0) < 0) ColorRed else ColorP2,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
