package com.jjmf.chihuancompose.Data.UseCase.Deuda

import com.jjmf.chihuancompose.Data.Model.Deuda
import com.jjmf.chihuancompose.Data.Repository.DeudaRepository
import javax.inject.Inject

class InsertDeuda @Inject constructor(
    private val repository: DeudaRepository
) {
    suspend operator fun invoke(deuda: Deuda) : String{
        return repository.insert(deuda)
    }
}