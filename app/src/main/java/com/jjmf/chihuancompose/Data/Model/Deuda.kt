package com.jjmf.chihuancompose.Data.Model

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Deuda(
    val titulo: String? = null,
    val fecha: Timestamp? = null,
    val idUsuario: String? = null,
    val idUsuario2: String? = null,
    val doble:Boolean = false,
    @get:Exclude val segundo:Boolean = false,
    @get:Exclude var id: String? = null,
) : Parcelable
