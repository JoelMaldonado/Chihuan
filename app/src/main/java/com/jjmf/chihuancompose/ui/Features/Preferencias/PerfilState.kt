package com.jjmf.chihuancompose.ui.Features.Preferencias

import com.jjmf.chihuancompose.Data.Model.Usuario

data class PerfilState(
    val usuario: Usuario? = null,
    val alertaQr:Boolean = false
)
