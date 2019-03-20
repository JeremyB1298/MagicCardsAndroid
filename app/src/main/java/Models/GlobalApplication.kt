package Models

import android.app.Application
import android.content.Context

class GlobalApplication : Application() {
    lateinit var appContext: Context

    override fun onCreate() {
        super.onCreate()
        appContext = getApplicationContext()

    }



}