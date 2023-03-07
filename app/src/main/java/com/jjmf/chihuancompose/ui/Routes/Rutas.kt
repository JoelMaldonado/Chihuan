package com.jjmf.chihuancompose.ui.Routes


sealed class Rutas(val route:String){

    object Bienvenida : Rutas("Bienvenida")
    object Splash : Rutas("Splash")
    object Login : Rutas("Login")
    object Menu : Rutas("Menu")
    object Perfil : Rutas("Perfil")
    object Deudas : Rutas("Deudas")
    object Diario : Rutas("Diario")
    object Reporte : Rutas("Reporte")
    object Detalle:Rutas("Detalle")
}
