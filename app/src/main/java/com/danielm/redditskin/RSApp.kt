package com.danielm.redditskin

import android.app.Application
import com.danielm.redditskin.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RSApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RSApp)
            modules(appModule)
        }
    }
}