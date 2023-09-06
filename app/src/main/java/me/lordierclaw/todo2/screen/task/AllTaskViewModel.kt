package me.lordierclaw.todo2.screen.task

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.lordierclaw.todo2.TodoApplication
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder

class AllTaskViewModel(private val repositoryBuilder: IRepositoryBuilder) : ViewModel() {
    val tasks: LiveData<List<Task>> get() = repositoryBuilder.taskRepository.getAllTask()

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repositoryBuilder.taskRepository.updateTask(task)
        }
    }

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val application = this.application as TodoApplication
            return AllTaskViewModel(application.repositoryBuilder) as T
        }
    }
}