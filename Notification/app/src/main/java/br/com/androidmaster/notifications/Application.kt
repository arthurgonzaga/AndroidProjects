package br.com.androidmaster.notifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat

class Application : Application(){

    override fun onCreate() {
        super.onCreate()


        createNotificationChannel(
            getString(R.string.CHANNEL_1_ID),
            getString(R.string.CHANNEL_1_NAME),
            getString(R.string.CHANNEL_1_DESCRIPTION),
            NotificationManagerCompat.IMPORTANCE_DEFAULT
        )

        createNotificationChannel(
            getString(R.string.CHANNEL_2_ID),
            getString(R.string.CHANNEL_2_NAME),
            getString(R.string.CHANNEL_2_DESCRIPTION),
            NotificationManagerCompat.IMPORTANCE_HIGH
        )
    }

    /**
     *   Will only create a Channel if there is no Channel registered
     *   on the system yet.
     */
    private fun createNotificationChannel(
        id: String,
        name: String,
        description: String,
        importance: Int
    ) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // Set the information
            val mChannel = NotificationChannel(id, name, importance)
            mChannel.description = description

            // Register the channel
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

}