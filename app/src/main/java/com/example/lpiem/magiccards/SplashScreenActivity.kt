package com.example.lpiem.magiccards

import Managers.UserManager
import views.BottomNavigationActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Observer
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController

class SplashScreenActivity : AppCompatActivity(),InterfaceCallBackController {
    override fun onWorkDone(result: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        val controller = MagicCardRetrofitController(this)
        UserManager.listCards!!.observe(this, Observer {
            start()
        })
        controller.callWS2()
        controller.userDecks()
    }


    fun start() {
        startActivity(Intent(this@SplashScreenActivity, BottomNavigationActivity::class.java))
        //finish this activity
        finish()
    }
}
