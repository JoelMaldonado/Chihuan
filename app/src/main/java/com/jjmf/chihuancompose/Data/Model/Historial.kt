package com.jjmf.chihuancompose.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.jjmf.chihuancompose.Util.toFormat

data class Historial(
    val fecha: Timestamp? = null,
    val dinero: Double? = null,
    val descripcion: String? = null,
    val idDeuda: String? = null,
    @get:Exclude var id: String? = null,
){

    fun getFecha(time: Timestamp): Fecha {
        val date = time.toDate()
        return Fecha(
            diaNum = "dd".toFormat(date),
            mes = "MM".toFormat(date),
            anio = "yyyy".toFormat(date),
            hora = "HH".toFormat(date),
            min = "mm".toFormat(date),
            dia = "EEEE".toFormat(date),
        )
    }
}