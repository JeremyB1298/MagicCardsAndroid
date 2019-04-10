package com.example.lpiem.magiccards

import viewModel.ShopViewModel
import android.app.Application
import android.os.UserManager
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class MagicApp : Application(), LifecycleOwner {
    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()

        ShopViewModel.initialize()
        Managers.UserManager.initialize()

    }

}