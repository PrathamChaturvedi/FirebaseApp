package com.example.firebaseapp.firebase

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging
import com.example.firebaseapp.R
import com.example.firebaseapp.model.ItemEntity
import com.example.firebaseapp.utils.FcmEvent


class MyFirebaseMessagingService : FirebaseMessagingService() {

    val TAG = "asfdasdfasdf"

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification?.let {
            if (it.title != null && it.body != null) {
                val itemEntity =
                    ItemEntity(
                        content = it.body.toString(),
                        timestamp = it.title.toString()
                    )
                Log.d(TAG, "Message Notification Title: ${it.title}")
                Log.d(TAG, "Message Notification Body: ${it.body}")
                FcmEvent.itemInserted.postValue(itemEntity)
            }
        }


        val notification = NotificationCompat.Builder(applicationContext,"1")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(message.notification?.title ?: "Test")
            .setContentText(message.notification?.body ?: "Test")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager : NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1,notification.build())
    }
}

