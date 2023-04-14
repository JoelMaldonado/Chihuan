package com.jjmf.chihuancompose.ui.Routes


sealed class Rutas(val route:String){

    object Bienvenida : Rutas("Bienvenida")
    object Splash : Rutas("Splash")
    object Login : Rutas("Login")
    object Registro : Rutas("Registro")
    object Preferencia : Rutas("Perfil"){
        object Moneda:Rutas("Moneda")
        object Sugerencia:Rutas("Sugerencia"){
            object Home:Rutas("HomeSugerencia")
            object Agregar:Rutas("AgregarSugerencia")
        }
    }
    object Deudas : Rutas("Deudas")
    object Detalle:Rutas("Detalle")
}
