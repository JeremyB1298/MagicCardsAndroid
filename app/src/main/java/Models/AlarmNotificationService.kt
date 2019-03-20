package Models

import android.app.IntentService
import android.app.NotificationManager
import android.content.Intent
import android.app.PendingIntent
import android.content.Context
import com.example.lpiem.magiccards.MainActivity
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.app.NotificationCompat
import com.example.lpiem.magiccards.R


class AlarmNotificationService : IntentService("AlarmNotificationService") {

    private lateinit var alarmNotificationManager: NotificationManager

    companion object {
        val NOTIFICATION_ID: Int = 1
    }

    override fun onCreate() {
        super.onCreate()
        sendNotification("Wake Up! Wake Up! Alarm started!!")
    }

    override fun onHandleIntent(intent: Intent?) {
        sendNotification("Wake Up! Wake Up! Alarm started!!");
    }

    fun sendNotification(msg: String) {
        alarmNotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //get pending intent
        val contentIntent = PendingIntent.getActivity(this, 0,
                Intent(this, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        //Create notification
        val alamNotificationBuilder = NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_sword)
                .setStyle(NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg).setAutoCancel(true)
        alamNotificationBuilder.setContentIntent(contentIntent)

        //notiy notification manager about new notification
        alarmNotificationManager.notify(NOTIFICATION_ID, alamNotificationBuilder.build())
    }

}