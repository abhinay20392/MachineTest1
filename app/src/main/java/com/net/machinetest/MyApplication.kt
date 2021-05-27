package com.net.machinetest

import android.app.Application
import com.example.machinetest1.BuildConfig
import com.example.machinetest1.R
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    /**
     * Initializes the Timber libarary
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * Initializing calligraphy library which is used to add custom fonts
     */
    private fun initCalligraphyLibrary() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setFontAttrId(R.attr.fontPath)
                            .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                            .build()
                    )
                )
                .build()
        )
    }
}