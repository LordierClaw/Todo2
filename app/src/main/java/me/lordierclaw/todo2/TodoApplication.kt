package me.lordierclaw.todo2

import android.app.Application
import me.lordierclaw.todo2.data.local.LocalDatabase

class TodoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        LocalDatabase.getInstance(applicationContext)
    }
}