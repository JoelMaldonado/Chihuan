package com.jjmf.chihuancompose.Data.UseCase.Deuda

import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Repository.DeudaRepository
import javax.inject.Inject

class UpdateDeuda @Inject constructor(
    private val repository: DeudaRepository
) {
    suspend operator fun invoke(deuda: Deuda){
        repository.update(deuda)
    }
}