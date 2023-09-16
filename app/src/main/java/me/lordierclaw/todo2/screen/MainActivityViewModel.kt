package me.lordierclaw.todo2.screen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.lordierclaw.todo2.TodoApplication
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder

class MainActivityViewModel(private val repositoryBuilder: IRepositoryBuilder) : ViewModel() {

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val application = this.application as TodoApplication
            return MainActivityViewModel(application.repositoryBuilder) as T
        }
    }
}