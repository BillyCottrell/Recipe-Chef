package com.codexive.recipechef

import android.app.Application
import com.codexive.recipechef.injection.component.DaggerDatabaseComponent
import com.codexive.recipechef.injection.component.DatabaseComponent
import com.codexive.recipechef.injection.module.DatabaseModule

class App : Application() {
    companion object {
        lateinit var component: DatabaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerDatabaseComponent
            .builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }
}