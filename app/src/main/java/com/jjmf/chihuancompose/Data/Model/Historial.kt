package com.jjmf.chihuancompose.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

data class Historial(
    val fecha: Timestamp? = null,
    val dinero: Double? = null,
    val descripcion: String? = null,
    val idDeuda: String? = null,
    @get:Exclude var id: String? = null,
)