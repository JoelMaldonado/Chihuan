package com.jjmf.chihuancompose.ui.Features.Deudas

import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Usuario

data class DeudaState(
    val cargando: Boolean = false,
    val listado: List<Deuda> = emptyList(),
    val usuario: Usuario? = null
)