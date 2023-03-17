package com.jjmf.chihuancompose.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.jjmf.chihuancompose.Util.toFormat
import java.util.*

data class Historial(
    val fecha: Timestamp? = null,
    val dinero: Double? = null,
    val descripcion: String? = null,
    val idDeuda: String? = null,
    @get:Exclude var id: String? = null,
){
}

fun Timestamp?.getFecha(): Fecha {
    val date = this?.toDate()
    return if (date !=null) Fecha(
        diaNum = "dd".toFormat(date),
        mes = "MM".toFormat(date),
        anio = "yyyy".toFormat(date),
        hora = "HH".toFormat(date),
        min = "mm".toFormat(date),
        dia = "EEEE".toFormat(date)
    )else Fecha(
        diaNum = "dd".toFormat(Date(System.currentTimeMillis())),
        mes = "MM".toFormat(Date(System.currentTimeMillis())),
        anio = "yyyy".toFormat(Date(System.currentTimeMillis())),
        hora = "HH".toFormat(Date(System.currentTimeMillis())),
        min = "mm".toFormat(Date(System.currentTimeMillis())),
        dia = "EEEE".toFormat(Date(System.currentTimeMillis()))
    )
}