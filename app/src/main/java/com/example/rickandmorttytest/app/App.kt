package com.example.rickandmorttytest.app

import android.app.Application
import com.example.rickandmorttytest.di.component.AppComponent
import com.example.rickandmorttytest.di.component.DaggerAppComponent
import com.example.rickandmorttytest.di.module.AppModule
import com.example.rickandmorttytest.model.SessionRunTime


class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        lateinit var appComponent: AppComponent
            private set

        var sessionRunTime: SessionRunTime = SessionRunTime("", "")
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupGraphApplicationComponent()
    }

    private fun setupGraphApplicationComponent() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    }

    fun setSession(email: String, password: String) {
        sessionRunTime.email = email
        sessionRunTime.password = password
    }
}