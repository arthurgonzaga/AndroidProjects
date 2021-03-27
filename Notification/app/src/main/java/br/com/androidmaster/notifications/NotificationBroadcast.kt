package br.com.androidmaster.notifications

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationBroadcast: BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("AGR", "onReceive: received")
        if (intent != null && context != null) {
            Log.d("AGR", "onReceive: not null")
            createNotification(
                context,
                intent.getIntExtra(MainActivity.EXTRA_NOTIFICATION_ID, 200)
            )
        }
    }



    fun createNotification(context: Context, id: Int){
        val CHANNEL_ID = context.getString(R.string.CHANNEL_2_ID)

        // Define the activity
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
        val pendingIntent = PendingIntent.getActivity(context, 0,intent,0)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_cake_24)
                .setContentTitle("Title test")
                .setContentText("This is the content of the notification")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)){
            // Remove the last notification
            cancel(id)

            // Show the notification
            notify(id, builder.build())
        }
    }

}