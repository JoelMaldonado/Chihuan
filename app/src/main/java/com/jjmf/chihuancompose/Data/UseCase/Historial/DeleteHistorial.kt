package com.jjmf.chihuancompose.Data.UseCase.Historial

import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.Repository.HistorialRepository
import javax.inject.Inject

class DeleteHistorial @Inject constructor(
    private val repository: HistorialRepository
) {
    suspend operator fun invoke(idDeuda:String){
        repository.getList(idDeuda).forEach {
            repository.delete(it)
        }
    }
}