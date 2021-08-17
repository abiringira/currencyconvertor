package com.paypay.currency.convertor

import android.app.Application
import com.google.android.datatransport.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class CurrencyConvertor : Application() {
    override fun onCreate() {
        super.onCreate()

        installLogging()
    }


    private fun installLogging() {
        if (BuildConfig.DEBUG) {
            // TODO - Check if you need non-debug tree line following:
            // https://github.com/JakeWharton/timber/blob/master/timber-sample/src/main/java/com/example/timber/ExampleApp.java
            Timber.plant(DebugTree())
        }
    }


}
