package com.jjmf.chihuancompose.Data.Model

import com.google.gson.annotations.SerializedName


class Paises : ArrayList<Pais>()

data class Pais(
    @SerializedName("nativeName") val nombre: String,
    @SerializedName("name") val fullName: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String,
    @SerializedName("numericCode") val numericCode: String,
    @SerializedName("flags") val flags: Flags,
    @SerializedName("currencies") val moneda: (List<Moneda>)? = null,
    @SerializedName("languages") val lenguaje: List<Lenguaje>,
)
data class Lenguaje(
    @SerializedName("iso639_1") val iso639_1: String,
    @SerializedName("iso639_2") val iso639_2: String,
    @SerializedName("name") val name: String,
    @SerializedName("nativeName") val nativeName: String
)
data class Moneda(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String
)
data class Flags(
    @SerializedName("png") val png: String
)