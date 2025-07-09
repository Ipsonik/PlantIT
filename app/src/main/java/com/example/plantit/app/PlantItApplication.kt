package com.example.plantit.app

import android.app.Application
import com.example.plantit.core.di.dataModule
import com.example.plantit.core.di.domainModule
import com.example.plantit.core.di.networkModule
import com.example.plantit.core.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class PlantItApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@PlantItApplication)
            modules(
                networkModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}