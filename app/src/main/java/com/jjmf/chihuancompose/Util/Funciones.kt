package com.jjmf.chihuancompose.Util

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.google.firebase.Timestamp
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


fun Double.redondear(decimalesRequeridos: Int = 2): Double{
    return this.toBigDecimal().setScale(decimalesRequeridos, RoundingMode.HALF_UP).toDouble()
}

fun Double.invertir() = if (this > 0) -this else this - this * 2

fun Date?.toFecha(pattern:String = "dd/MM/yyyy HH:mm") = if (this!=null) SimpleDateFormat(pattern, Locale.getDefault()).format(this) else "Fecha no encontrada"

fun getFecha(pattern: String = "dd/MM/yyyy HH:mm") = SimpleDateFormat(pattern, Locale.getDefault()).format(System.currentTimeMillis())


fun toNumero(numString: String): TextFieldValue {
    val data = if (numString.trim() == "") "0"
    else if (numString.trim().length == 2) {
        val number1: String = numString[0].toString()
        val number2: String = numString[1].toString()
        if (number1 == "0") number2 else numString
    } else numString
    val valor = if (data.split("").any { it == "." }) {
        val info1 = data.split(".")[0]
        val info2 = data.split(".")[1]
        if (info2.length < 3) "$info1.$info2" else "$info1.${info2.substring(0, 2)}"
    } else data
    return TextFieldValue(text = valor, selection = TextRange(valor.length))
}

fun esNumero(s: String): Boolean {
    return try {
        s.toDouble()
        true
    } catch (e: NumberFormatException) {
        false
    }
}