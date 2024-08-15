package com.dantrap.template

import android.app.Application
import com.dantrap.template.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
