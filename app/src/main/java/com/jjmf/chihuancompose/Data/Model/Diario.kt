package com.jjmf.chihuancompose.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

data class Diario(
    val monto:Double? = null,
    val descrip:String? = null,
    val time:Timestamp? = null,
    val fecha:String? = null,
    val hora:String? = null,
    val idUsuario: String? = null,
    @get:Exclude var id:String? = null
)
