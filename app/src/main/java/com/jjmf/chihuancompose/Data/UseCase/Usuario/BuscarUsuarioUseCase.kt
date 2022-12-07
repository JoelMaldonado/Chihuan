package com.jjmf.chihuancompose.Data.UseCase.Usuario

import com.jjmf.chihuancompose.Core.EstadosResult
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import javax.inject.Inject

class BuscarUsuarioUseCase @Inject constructor(
    private val repository: UsuarioRepository,
) {
    suspend operator fun invoke(codigo: String): EstadosResult<Usuario> {
        val list = repository.getListUsuarios()
        return try {
            if (codigo in list.map { it.id }) {
                EstadosResult.Correcto(list[list.indexOfFirst { codigo == it.id }])
            }else{
                EstadosResult.Error("No se encontro al usuario")
            }
        }catch (e:Exception){
            EstadosResult.Error(e.message.toString())
        }
    }
}