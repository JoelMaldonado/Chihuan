package com.jjmf.chihuancompose.ui.Features.Diario

import com.jjmf.chihuancompose.Data.Model.Diario

data class DiarioState(
    val alertaGasto:Boolean = false,
    val alertaIngreso:Boolean = false,
    val listado:List<Diario> = emptyList(),
    val contador:Double = 0.0
)
