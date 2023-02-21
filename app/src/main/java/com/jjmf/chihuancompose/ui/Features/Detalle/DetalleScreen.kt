package com.jjmf.chihuancompose.ui.Features.Detalle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.ui.Features.Detalle.Components.ItemHistorial
import com.jjmf.chihuancompose.ui.Features.Detalle.Components.ModificarDeudaBottom
import com.jjmf.chihuancompose.ui.Features.Deudas.Components.TituloAgregarDeuda

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetalleScreen(
    deuda: Deuda,
    back:()->Unit,
    viewModel: DetalleViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getList(idDeuda = deuda.id ?: "")
    }

    val context = LocalContext.current
    viewModel.bottomState = rememberBottomSheetScaffoldState()
    viewModel.coroutine = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = viewModel.bottomState,
        sheetContent = {
            ModificarDeudaBottom(deuda = deuda)
        },
        sheetPeekHeight = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TituloAgregarDeuda("Detalles")
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                if (viewModel.state.isEmpty()) {
                    item {
                        Text(text = "Sin resultado")
                    }
                }
                items(viewModel.state) {
                    ItemHistorial(it)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FloatingActionButton(
                    onClick = {
                        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
                            titleText = "Eliminar Deuda"
                            contentText = "Se elimininara '${deuda.titulo}', ¿Estas seguro?"
                            setConfirmButton("Eliminar"){
                                dismissWithAnimation()
                                viewModel.delete(deuda.id!!)
                                back()
                            }
                            setCancelButton("Cancelar"){
                                dismissWithAnimation()
                            }
                            show()
                        }
                    },
                    backgroundColor = Color.Red
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                FloatingActionButton(
                    onClick = viewModel::open,
                    backgroundColor = Color.Green
                ) {
                    Icon(
                        imageVector = Icons.Default.Money,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}
