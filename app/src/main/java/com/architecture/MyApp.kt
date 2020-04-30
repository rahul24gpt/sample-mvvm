package com.architecture

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object{
        lateinit var mInstance: MyApp
        fun getInstance(): MyApp {
            return mInstance
        }
    }
}