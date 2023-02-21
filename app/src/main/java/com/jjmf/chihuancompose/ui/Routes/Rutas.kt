package com.jjmf.chihuancompose.ui.Routes

import com.jjmf.chihuancompose.Data.Model.Deuda

sealed class Rutas(val route:String){

    object Splash : Rutas("Splash")
    object Login : Rutas("Login")
    object Menu : Rutas("Menu")
    object Deudas : Rutas("Deudas")
    object Diario : Rutas("Diario")
    object Detalle:Rutas("Detalle")
}
