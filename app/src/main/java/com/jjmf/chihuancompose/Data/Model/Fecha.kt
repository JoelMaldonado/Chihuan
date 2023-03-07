package com.jjmf.chihuancompose.Data.Model

import com.jjmf.chihuancompose.Util.toFormat
import java.util.*

data class Fecha(
    val diaNum: String = "dd".toFormat(Date(System.currentTimeMillis())),
    val mes: String = "MM".toFormat(Date(System.currentTimeMillis())),
    val anio: String = "yyyy".toFormat(Date(System.currentTimeMillis())),
    val hora: String = "HH".toFormat(Date(System.currentTimeMillis())),
    val min: String = "mm".toFormat(Date(System.currentTimeMillis())),
    val dia: String = "EEEE".toFormat(Date(System.currentTimeMillis())),
)