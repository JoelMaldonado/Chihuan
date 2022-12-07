package com.jjmf.chihuancompose.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

data class Diario(
    @get:Exclude var id: String? = null,
    val idUser: String? = null,
    val monto:Double? = null,
    val descrip:String? = null,
    val fecha:String? = null,
    val hora:String? = null
)
