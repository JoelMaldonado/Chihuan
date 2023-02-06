package com.jjmf.chihuancompose.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.jjmf.chihuancompose.ui.theme.ColorP2
import com.jjmf.chihuancompose.ui.viewModel.LoginViewModel
import kotlinx.coroutines.*

@Composable
fun SplashScreen(
    toLogin: () -> Unit,
    toMenu: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {


    val state = viewModel.state

    if (state.usuario != null){
        LaunchedEffect(key1 = true){
            toMenu()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP2)
    )

    val auth = FirebaseAuth.getInstance()

    val context = LocalContext.current
    if (!state.mensajeError.isNullOrEmpty()){
        LaunchedEffect(key1 = state.mensajeError){
            Toast.makeText(context, state.mensajeError, Toast.LENGTH_SHORT).show()
            auth.currentUser?.delete()
            toLogin()
        }
    }

    LaunchedEffect(key1 = true) {
        if (auth.currentUser!=null){
            viewModel.getId(auth.currentUser!!.email!!)
        }else{
            toLogin()
        }
    }
}