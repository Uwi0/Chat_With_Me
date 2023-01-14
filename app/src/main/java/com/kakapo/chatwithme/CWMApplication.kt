package com.kakapo.chatwithme

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CWMApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    private fun initLogger(){
        if(BuildConfig.DEBUG){

        }
    }
}