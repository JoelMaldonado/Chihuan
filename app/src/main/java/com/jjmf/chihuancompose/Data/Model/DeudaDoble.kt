package com.jjmf.chihuancompose.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

data class DeudaDoble(
    val nombre: String? = null,
    val nombre2: String? = null,

    val dinero: Double? = null,
    val fecha: Timestamp? = null,

    val idUsuario: String? = null,
    val idUsuario2: String? = null,

    @get:Exclude var id: String? = null,
)
