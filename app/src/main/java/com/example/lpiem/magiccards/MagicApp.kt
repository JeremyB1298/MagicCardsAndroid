package com.example.lpiem.magiccards

import viewModel.ShopViewModel
import android.app.Application
import android.os.UserManager

class MagicApp : Application(){

    override fun onCreate() {
        super.onCreate()

        ShopViewModel.initialize()
        Managers.UserManager.initialize()
    }

}