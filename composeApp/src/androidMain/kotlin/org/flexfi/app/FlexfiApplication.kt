package org.flexfi.app

import android.app.Application
import org.flexfi.app.core.injection.initKoin
import org.koin.android.ext.koin.androidContext

class FlexfiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FlexfiApplication)
        }
    }
}