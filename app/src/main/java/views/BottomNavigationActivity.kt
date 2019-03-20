package views

import Fragments.CardRecyclerViewFragment
import Managers.UserManager
import Fragments.FragmentHome
import Fragments.FragmentShop
import Models.User
import android.app.PendingIntent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lpiem.magiccards.R
import java.util.*
import com.example.lpiem.magiccards.MainActivity
import Models.AlarmReceiver
import android.content.Intent
import android.widget.Toast
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.content.Context
import Models.AlarmNotificationService
import Models.NotificationUtils
import android.R.string.cancel
import android.app.NotificationManager










class BottomNavigationActivity : AppCompatActivity() {

    private val mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
    private var mNotified = false

    private var content: FrameLayout? = null
    private var user: User? = null
    private var pendingIntent: PendingIntent? = null
    private val ALARM_REQUEST_CODE = 133


    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_1 -> {

                    val fragment = FragmentHome.newInstance(user!!)
                    addFragment(fragment)
                    return true
                }
                R.id.navigation_2 -> {
                    val fragment = CardRecyclerViewFragment()
                    addFragment(fragment)
                    return true
                }
                R.id.navigation_3 -> {
                    var fragment = FragmentShop()
                    addFragment(fragment)
                    return true
                }
            }
            return false
        }

    }

    /**
     * add/replace fragment in container [framelayout]
     */
    private fun addFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .commit()
    }
    private fun addHomeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        content = findViewById(R.id.content) as FrameLayout
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        user = UserManager.user
        val fragment = FragmentHome.newInstance(user!!)

        val alarmIntent = Intent(this@BottomNavigationActivity, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this@BottomNavigationActivity, ALARM_REQUEST_CODE, alarmIntent, 0)
        triggerAlarmManager(5);

        /*if (!mNotified) {
            NotificationUtils().setNotification(mNotificationTime, this@BottomNavigationActivity)
        }*/
        addHomeFragment(fragment)
    }

    fun triggerAlarmManager(alarmTriggerTime: Int) {
        // get a Calendar object with current time
        val cal = Calendar.getInstance()
        // add alarmTriggerTime seconds to the calendar object
        cal.add(Calendar.SECOND, alarmTriggerTime)

        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager//get instance of alarm manager
        manager.set(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pendingIntent)//set alarm manager with entered timer by converting into milliseconds

        //Toast.makeText(this, "Alarm Set for $alarmTriggerTime seconds.", Toast.LENGTH_SHORT).show()
    }

    fun stopAlarmManager() {

        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.cancel(pendingIntent)//cancel the alarm manager of the pending intent


        //remove the notification from notification tray
        val notificationManager = this
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(AlarmNotificationService.NOTIFICATION_ID)

        Toast.makeText(this, "Alarm Canceled/Stop by User.", Toast.LENGTH_SHORT).show()
    }

}