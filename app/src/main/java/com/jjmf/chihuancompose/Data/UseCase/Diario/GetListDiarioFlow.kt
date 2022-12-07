package com.jjmf.chihuancompose.Data.UseCase.Diario

import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Data.Repository.DiarioRepository
import javax.inject.Inject

class GetListDiarioFlow @Inject constructor(
    private val repository: DiarioRepository,
) {
    suspend operator fun invoke() = repository.getListFlow(prefs.getId())
}