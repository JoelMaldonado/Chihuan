package com.jjmf.chihuancompose.Data.Model

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Deuda(
    val titulo: String? = null,
    val dinero: Double? = null,
    val fecha: Timestamp? = null,
    val idUsuario: String? = null,
    @get:Exclude var id: String? = null,
) : Parcelable
