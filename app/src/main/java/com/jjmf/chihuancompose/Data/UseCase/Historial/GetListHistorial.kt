package com.jjmf.chihuancompose.Data.UseCase.Historial

import com.jjmf.chihuancompose.Data.Model.Historial
import com.jjmf.chihuancompose.Data.Repository.HistorialRepository
import javax.inject.Inject

class GetListHistorial @Inject constructor(
    private val repository: HistorialRepository
){
    suspend operator fun invoke(idDeuda:String):List<Historial>{
        return repository.getList(idDeuda)
    }
}