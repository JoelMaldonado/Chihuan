package com.jjmf.chihuancompose.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class Deuda(
    val titulo: String? = null,
    val dinero: Double? = null,
    val fecha: Timestamp? = null,
    val idUsuario: String? = null,
    @get:Exclude var id: String? = null,
) : Serializable
