package com.jjmf.chihuancompose.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jjmf.chihuancompose.Util.getDia
import com.jjmf.chihuancompose.ui.Routes.NavegacionesScreen
import com.jjmf.chihuancompose.ui.theme.ChihuanComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChihuanComposeTheme {
                NavegacionesScreen(this)
            }
        }
    }
}