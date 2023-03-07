package com.jjmf.chihuancompose.ui.Features.Perfil

import com.jjmf.chihuancompose.Data.Model.Usuario

data class PerfilState(
    val usuario: Usuario? = null,
    val alertaQr:Boolean = false
)
