package com.jjmf.chihuancompose.ui.Routes


sealed class Rutas(val route:String){

    object Bienvenida : Rutas("Bienvenida")
    object Splash : Rutas("Splash")
    object Login : Rutas("Login")
    object Registro : Rutas("Registro")
    object Preferencia : Rutas("Perfil"){
        object Moneda:Rutas("Moneda")
    }
    object Deudas : Rutas("Deudas")
    object Detalle:Rutas("Detalle")
}
