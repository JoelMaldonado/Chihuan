package com.jjmf.chihuancompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.Util.setLocale
import com.jjmf.chihuancompose.ui.Routes.NavegacionesScreen
import com.jjmf.chihuancompose.ui.theme.ChihuanComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocale(context = this, language = prefs.getIdioma())
        setContent {
            ChihuanComposeTheme {
                NavegacionesScreen(this)
            }
        }
    }
}