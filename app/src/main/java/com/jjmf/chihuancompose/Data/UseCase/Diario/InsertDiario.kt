package com.jjmf.chihuancompose.Data.UseCase.Diario

import com.jjmf.chihuancompose.Data.Model.Diario
import com.jjmf.chihuancompose.Data.Repository.DiarioRepository
import javax.inject.Inject

class InsertDiario @Inject constructor(
    private val repository: DiarioRepository
) {
    suspend operator fun invoke(diario: Diario){
        repository.insert(diario)
    }
}