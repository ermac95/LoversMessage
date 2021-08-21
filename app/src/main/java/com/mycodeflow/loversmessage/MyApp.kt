package com.mycodeflow.loversmessage

import android.app.Application
import com.mycodeflow.loversmessage.di.AppComponent
import com.mycodeflow.loversmessage.di.DaggerAppComponent

class MyApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.factory()
            .create(applicationContext)
    }
}