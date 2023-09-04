package me.lordierclaw.todo2

import android.app.Application
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder
import me.lordierclaw.todo2.data.local.LocalDatabase
import me.lordierclaw.todo2.data.local.repository.LocalRepositoryBuilder

class TodoApplication: Application() {

    private var _repositoryBuilder: IRepositoryBuilder? = null
    val repositoryBuilder: IRepositoryBuilder get() = _repositoryBuilder!!

    override fun onCreate() {
        super.onCreate()
        LocalDatabase.getInstance(applicationContext)
        _repositoryBuilder = LocalRepositoryBuilder(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        _repositoryBuilder = null
    }
}