package com.jjmf.chihuancompose.ui.Screens.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.chihuancompose.Data.Model.Usuario
import com.jjmf.chihuancompose.Data.Repository.UsuarioRepository
import com.jjmf.chihuancompose.Data.UseCase.Usuario.GetId
import com.jjmf.chihuancompose.Data.UseCase.Usuario.InsertUsuarioUseCase
import com.jjmf.chihuancompose.Data.UseCase.Usuario.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val insertUsuarioUseCase: InsertUsuarioUseCase,
    private val getIdUseCase: GetId
) : ViewModel(){

    fun insertar(usuario: Usuario) {
        viewModelScope.launch {
            if (loginUseCase(usuario)) insertUsuarioUseCase(usuario)
        }
    }

    fun getId(email: String) {
        viewModelScope.launch {
            getIdUseCase(email)
        }
    }
}