package com.dantrap.cryptotracker

import android.app.Application
import com.dantrap.cryptotracker.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
