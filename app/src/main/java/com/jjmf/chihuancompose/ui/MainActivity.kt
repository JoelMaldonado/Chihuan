package com.jjmf.chihuancompose.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs
import com.jjmf.chihuancompose.BuildConfig
import com.jjmf.chihuancompose.Util.setLocale
import com.jjmf.chihuancompose.ui.Routes.NavegacionesScreen
import com.jjmf.chihuancompose.ui.theme.ChihuanComposeTheme
import com.jjmf.chihuancompose.ui.theme.ColorP1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()
        if (BuildConfig.VERSION_CODE < 4) {
            SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE).apply {
                titleText = "Nueva version"
                contentText = "Existe una nueva version, es necesario actualizar"
                confirmButtonBackgroundColor = ColorP1.hashCode()
                setConfirmButton("Ir a play Store") {
                    val uri =
                        Uri.parse("https://play.google.com/store/apps/details?id=com.jjmf.chihuancompose&pli=1")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    dismissWithAnimation()
                }
                setCancelable(false)
                show()
            }
        }
    }

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