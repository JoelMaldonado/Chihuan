package com.jjmf.chihuancompose.Application

import android.content.Context

class Preferencias(context: Context) {

    private val SHARED_NAME = "MYDTB"

    private val KEY_TOKEN = "KEY_TOKEN"
    private val KEY_BIENVENIDA = "KEY_BIENVENIDA"
    private val KEY_ID = "KEY_ID"
    private val KEY_IMG = "KEY_IMG"
    private val KEY_BOOL = "KEY_BOOL"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)


    fun saveID(id: String) {
        storage.edit().putString(KEY_ID, id).apply()
    }

    fun getId() = storage.getString(KEY_ID, "") ?: "No se encontro"
    fun saveToken(token:String) = storage.edit().putString(KEY_TOKEN, token).apply()
    fun getToken() = storage.getString(KEY_TOKEN, "") ?: "No se encontro"
    fun saveBienvenida(bool:Boolean) = storage.edit().putBoolean(KEY_BIENVENIDA, bool).apply()
    fun getBienvenida() = storage.getBoolean(KEY_BIENVENIDA, false)
}