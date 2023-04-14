package com.jjmf.chihuancompose.Application

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class BaseApp : Application() {

    companion object {
        lateinit var prefs: Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Preferencias(applicationContext)
        MobileAds.initialize(this)
        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(Collections.singletonList("DEVICE ID"))
            .build()
        MobileAds.setRequestConfiguration(configuration)
    }
}