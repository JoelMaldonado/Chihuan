package com.jjmf.chihuancompose.Data.UseCase.Usuario

import com.jjmf.chihuancompose.Application.BaseApp
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import javax.inject.Inject

class GetId @Inject constructor(
    private val repository: UsuarioRepository
) {
    suspend operator fun invoke(correo:String){
        val id = repository.getListUsuarios().filter { it.correo == correo }[0]
        BaseApp.prefs.saveID(id.id.toString())
    }
}