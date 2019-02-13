package com.example.lpiem.magiccards

import Singletons.ShopManager
import android.app.Application

class MagicApp : Application(){

    override fun onCreate() {
        super.onCreate()

        ShopManager.initialize()

    }

}