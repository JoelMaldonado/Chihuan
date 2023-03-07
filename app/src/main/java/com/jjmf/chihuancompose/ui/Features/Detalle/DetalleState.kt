package com.jjmf.chihuancompose.ui.Features.Detalle

import com.jjmf.chihuancompose.Data.Model.Historial

data class DetalleState(
    val listado:List<Historial> = emptyList(),
    val alertaModificar:Boolean = false,
    val alertaEliminar:Boolean = false,
    val mantenimiento:Boolean = false
)
