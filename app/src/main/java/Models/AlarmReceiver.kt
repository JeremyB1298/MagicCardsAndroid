package Models

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    private val mNotificationTime = Calendar.getInstance().timeInMillis //Set after 5 seconds from the current time.
    private var t = false
    override fun onReceive(context: Context?, intent: Intent?) {

            Toast.makeText(context, "ALARM!! ALARM!!", Toast.LENGTH_SHORT).show();
            val service = Intent(context, NotificationService::class.java)
            service.putExtra("reason", intent?.getStringExtra("reason"))
            service.putExtra("timestamp", intent?.getLongExtra("timestamp", 0))

            context?.startService(service)
    }

}