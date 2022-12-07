package com.jjmf.chihuancompose.Data.UseCase.Deuda

import com.jjmf.chihuancompose.Data.Repository.DeudaRepository
import javax.inject.Inject

class GetListDeudaFlow @Inject constructor(
    private val repository: DeudaRepository,
) {
    suspend operator fun invoke() = repository.getList()
}