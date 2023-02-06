package com.jjmf.chihuancompose.ui.controller

sealed class Rutas(val route:String){
    object Splash : Rutas("Splash")
    object Login : Rutas("Login")
    object Deudas : Rutas("Deudas")
}
