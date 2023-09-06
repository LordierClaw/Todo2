package me.lordierclaw.todo2.screen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.lordierclaw.todo2.TodoApplication
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.data.base.model.Subtask
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder

class MainActivityViewModel(private val repositoryBuilder: IRepositoryBuilder) : ViewModel() {

    fun getAllCategory() : LiveData<List<Category>> = repositoryBuilder.categoryRepository.getAllCategory()

    fun insertTaskWithSubtasks(task: Task, subtasks: List<Subtask>) {
        viewModelScope.launch {
            val id = repositoryBuilder.taskRepository.insertTask(task)
            subtasks.forEach {
                repositoryBuilder.subtaskRepository.insertSubtask(it.copy(taskId = id.toInt()))
            }
        }
    }

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val application = this.application as TodoApplication
            return MainActivityViewModel(application.repositoryBuilder) as T
        }
    }
}