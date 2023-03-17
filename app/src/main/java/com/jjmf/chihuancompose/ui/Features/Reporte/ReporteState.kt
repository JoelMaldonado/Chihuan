package com.jjmf.chihuancompose.ui.Features.Reporte

import com.jjmf.chihuancompose.Data.Model.Diario

data class ReporteState(
    val listado:List<Diario> = emptyList(),
    val ingresos:Double = 0.0,
    val gastos:Double= 0.0
)
