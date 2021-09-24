package com.cockroach

import android.app.Application
import com.cockroach.mvvmdemo.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication :Application(){
    override fun onCreate() {
        super.onCreate()

        // 若目前在開發狀態，初始化Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}