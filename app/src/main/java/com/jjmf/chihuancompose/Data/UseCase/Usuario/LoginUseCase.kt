package com.jjmf.chihuancompose.Data.UseCase.Usuario

import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UsuarioRepository
){
    suspend operator fun invoke(usuario: Usuario) = usuario.correo in repository.getListUsuarios().map { it.correo }
}