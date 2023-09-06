package me.lordierclaw.todo2.screen.task.dialog.addtask

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.lordierclaw.todo2.TodoApplication
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.data.base.model.Subtask
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder
import java.util.Date

class AddTaskDialogViewModel(private val repositoryBuilder: IRepositoryBuilder): ViewModel() {

    var categoryId: Int? = null
    var date: Date? = null
    var repeat: String? = null

    private val _subtasks: MutableLiveData<List<Subtask>> = MutableLiveData(listOf())
    private var subtaskCounter: Int = 0
    val subtasks: LiveData<List<Subtask>> get() = _subtasks

    fun newSubtask(defaultName: String = "New Subtask") {
        _subtasks.value = _subtasks.value?.toMutableList().also {
            it?.add(Subtask(id = subtaskCounter, name = "$defaultName $subtaskCounter", taskId = 0))
            subtaskCounter++
        }
    }

    fun deleteSubtask(subtask: Subtask) {
        _subtasks.value = _subtasks.value?.toMutableList().also {
            it?.remove(subtask)
        }
    }

    fun updateSubtask(subtask: Subtask) {
        _subtasks.value = _subtasks.value?.toMutableList().also { list ->
            list?.forEachIndexed { index, it ->
                if (it.id == subtask.id) {
                    list[index] = subtask
                    return@forEachIndexed
                }
            }
        }
    }

    fun getAllCategory() : LiveData<List<Category>> = repositoryBuilder.categoryRepository.getAllCategory()

    fun insertTaskWithSubtasks(taskName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = repositoryBuilder.taskRepository.insertTask(
                Task(
                    name = taskName,
                    categoryId = categoryId,
                    dueDate = date,
                    repeat = repeat
                )
            )
            val subtasksArray = _subtasks.value?.map { it.copy(id = 0, taskId = id) }?.toTypedArray()
            if (subtasksArray != null) {
                repositoryBuilder.subtaskRepository.insertSubtask(*subtasksArray)
            }
        }
    }

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val application = this.application as TodoApplication
            return AddTaskDialogViewModel(application.repositoryBuilder) as T
        }
    }
}