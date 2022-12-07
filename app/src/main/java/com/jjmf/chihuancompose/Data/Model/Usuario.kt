package com.jjmf.chihuancompose.Data.Model

import com.google.firebase.firestore.Exclude

data class Usuario(
    val nombres: String? = null,
    val apellido: String? = null,
    val foto: String? = null,
    val correo: String? = null,
    val pass: String? = null,
    @get: Exclude var id: String? = null,
)