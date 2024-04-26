package com.example.livebasketballcompose

import android.app.Application
import com.example.livebasketballcompose.appads.AppOpenManager
import com.google.android.gms.ads.MobileAds

class MyApp : Application() {

    private var appOpenManager: AppOpenManager? = null
    override fun onCreate() {
        super.onCreate()
        appOpenManager = AppOpenManager(this)
        MobileAds.initialize(this) {}
//        val prefs = Prefs(this)
//        AppOpenAdManager(this, "ca-app-pub-3940256099942544/9257395921")
    }
}