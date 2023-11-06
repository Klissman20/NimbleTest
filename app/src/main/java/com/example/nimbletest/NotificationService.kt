package com.example.nimbletest

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class NotificationService (
    private val context: Context
){
    @RequiresApi(Build.VERSION_CODES.M)
    private val notificationManager=context.getSystemService(NotificationManager::class.java)
    @RequiresApi(Build.VERSION_CODES.M)
    fun showBasicNotification(){
        val notification= NotificationCompat.Builder(context,"water_notification")
            .setContentTitle("Check your email.")
            .setContentText("We’ve email you instructions to reset your password.")
            .setSmallIcon(R.drawable.notification_icon)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
}