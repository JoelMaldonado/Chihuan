package com.jjmf.chihuancompose.Application

import android.content.Context
import com.google.gson.Gson
import com.jjmf.chihuancompose.Data.Model.Moneda
import com.jjmf.chihuancompose.Data.Model.Usuario
import java.util.*

class Preferencias(context: Context) {

    private val SHARED_NAME = "MYDTB"

    private val KEY_TOKEN = "KEY_TOKEN"
    private val KEY_BIENVENIDA = "KEY_BIENVENIDA"
    private val KEY_ID = "KEY_ID"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveToken(token: String) = storage.edit().putString(KEY_TOKEN, token).apply()
    fun getToken() = storage.getString(KEY_TOKEN, "") ?: "No se encontro"
    fun saveBienvenida(bool: Boolean) = storage.edit().putBoolean(KEY_BIENVENIDA, bool).apply()
    fun getBienvenida() = storage.getBoolean(KEY_BIENVENIDA, false)

    private val KEY_USER = "KEY_USER"
    fun saveUser(user: Usuario) = storage.edit().putString(KEY_USER, Gson().toJson(user)).apply()

    fun clearUser() {
        storage.edit().remove(KEY_USER).apply()
    }
    fun getUser(): Usuario? {
        val user = storage.getString(KEY_USER, null)
        return if (user != null) {
            Gson().fromJson(user, Usuario::class.java)
        } else null
    }

    private val KEY_MONEDA = "KEY_MONEDA"
    fun saveMoneda(moneda:Moneda) {
        val gson = Gson().toJson(moneda)
        save(KEY_MONEDA, gson)
    }
    fun getMoneda() : Moneda?{
        val moneda = get(KEY_MONEDA)
        return try {
            if (moneda!=null){
                val gson = Gson().fromJson(moneda, Moneda::class.java)
                gson
            }else null
        }catch (e:Exception){
            null
        }
    }

    private val KEY_IDIOMA = "KEY_IDIOMA"

    fun saveIdioma(idioma: String) = save(KEY_IDIOMA, idioma)
    fun getIdioma() = get(KEY_IDIOMA) ?: when(Locale.getDefault().language){
        "es" -> "es"
        "en" -> "en"
        else -> "en"
    }

    private fun save(key:String, valor:String){
        storage.edit().putString(key, valor).apply()
    }
    private fun get(key: String): String? {
        return storage.getString(key, null)
    }

}