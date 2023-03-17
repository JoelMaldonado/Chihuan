package com.jjmf.chihuancompose.ui.Features.Menu

import androidx.lifecycle.ViewModel
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repoUsuario: UsuarioRepository
) : ViewModel() {

}