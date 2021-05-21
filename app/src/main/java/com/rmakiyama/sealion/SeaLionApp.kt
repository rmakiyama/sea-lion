package com.rmakiyama.sealion

import android.app.Application
import timber.log.Timber

class SeaLionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
