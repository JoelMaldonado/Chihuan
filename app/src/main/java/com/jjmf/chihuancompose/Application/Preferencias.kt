package com.jjmf.chihuancompose.Application

import android.content.Context
import com.google.gson.Gson
import com.jjmf.chihuancompose.Data.Model.Moneda
import com.jjmf.chihuancompose.Data.Model.Usuario

class Preferencias(context: Context) {

    private val SHARED_NAME = "MYDTB"

    private val KEY_TOKEN = "KEY_TOKEN"
    private val KEY_BIENVENIDA = "KEY_BIENVENIDA"
    private val KEY_ID = "KEY_ID"
    private val KEY_RECUERDAME = "KEY_RECUERDAME"
    private val KEY_LOGIN_CORREO = "KEY_LOGIN_CORREO"
    private val KEY_LOGIN_PASS = "KEY_LOGIN_PASS"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)


    fun saveLoginCorreo(id: String) =storage.edit().putString(KEY_LOGIN_CORREO, id).apply()
    fun getLoginCorreo() = storage.getString(KEY_LOGIN_CORREO, "") ?: "No se encontro"

    fun saveLoginPass(id: String) =storage.edit().putString(KEY_LOGIN_PASS, id).apply()
    fun getLoginPass() = storage.getString(KEY_LOGIN_PASS, "") ?: ""
    fun saveToken(token: String) = storage.edit().putString(KEY_TOKEN, token).apply()
    fun getToken() = storage.getString(KEY_TOKEN, "") ?: "No se encontro"
    fun saveBienvenida(bool: Boolean) = storage.edit().putBoolean(KEY_BIENVENIDA, bool).apply()
    fun getBienvenida() = storage.getBoolean(KEY_BIENVENIDA, false)
    fun saveRecuerdame(bool: Boolean) = storage.edit().putBoolean(KEY_RECUERDAME, bool).apply()
    fun getRecuerdame() = storage.getBoolean(KEY_RECUERDAME, false)

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

    private fun save(key:String, valor:String){
        storage.edit().putString(key, valor).apply()
    }
    private fun get(key: String): String? {
        return storage.getString(key, null)
    }
}