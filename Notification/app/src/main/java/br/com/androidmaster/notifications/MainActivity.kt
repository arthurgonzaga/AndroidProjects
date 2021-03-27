package br.com.androidmaster.notifications

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var buttonDelete: Button

    // The IDs is incremented when new notifications appear
    var notificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        buttonDelete = findViewById(R.id.button3)
    }

    /**
     * This is just an example of a notification
     */
    fun createNotification1(view: View){
        val CHANNEL_ID = getString(R.string.CHANNEL_1_ID)

        // Define the activity
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
        val pendingIntent = PendingIntent.getActivity(this, 0,intent,0)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_cake_24)
            .setContentTitle("Title test")
            .setContentText("This is the content of the notification")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)){
            // Remove the last notification
            cancel(notificationId)
            notificationId++

            // Show the notification
            notify(notificationId, builder.build())
        }
    }

    fun removeNotification(view: View) {
        with(NotificationManagerCompat.from(this)){
            // Remove the last notification
            cancel(notificationId)
            notificationId--
        }
    }
}