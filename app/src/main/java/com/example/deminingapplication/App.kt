package com.example.deminingapplication

import android.app.Application
import com.example.deminingapplication.di.dataDi
import com.example.deminingapplication.di.domainDi
import com.example.deminingapplication.di.roomDi
import com.example.deminingapplication.di.sharedDI
import com.example.deminingapplication.di.uiDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(listOf(uiDi, domainDi, dataDi, roomDi, sharedDI))
        }
    }
}