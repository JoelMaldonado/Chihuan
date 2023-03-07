package com.jjmf.chihuancompose.Application

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jjmf.chihuancompose.Application.BaseApp.Companion.prefs

class MYFirebaseMessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("tagitoPush", remoteMessage.from.toString())

        if (remoteMessage.data.isNotEmpty()){
            Log.d("tagitoPush2", remoteMessage.data.toString())
        }
    }

    override fun onNewToken(token: String) {
        prefs.saveToken(token)
        super.onNewToken(token)
    }
}