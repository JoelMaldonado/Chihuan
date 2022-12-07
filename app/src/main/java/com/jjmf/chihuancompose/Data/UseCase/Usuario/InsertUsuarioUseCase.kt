package com.jjmf.chihuancompose.Data.UseCase.Usuario

import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import javax.inject.Inject

class InsertUsuarioUseCase @Inject constructor(
    private val repository: UsuarioRepository,
) {
    suspend operator fun invoke(usuario: Usuario) {
        repository.insert(usuario)
    }
}