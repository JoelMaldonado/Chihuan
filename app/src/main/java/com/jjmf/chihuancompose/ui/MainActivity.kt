package com.jjmf.chihuancompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jjmf.chihuancompose.ui.controller.NavegacionesScreen
import com.jjmf.chihuancompose.ui.theme.ChihuanComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChihuanComposeTheme {
                NavegacionesScreen()
            }
        }
    }
}