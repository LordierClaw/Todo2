package me.lordierclaw.todo2.screen.task.dialog.addtask

import android.app.Application
import androidx.lifecycle.LiveData
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
import me.lordierclaw.todo2.utils.list.BaseList
import java.util.Date

class AddTaskDialogViewModel(private val repositoryBuilder: IRepositoryBuilder): ViewModel() {

    var categoryId: Int? = null
    var date: Date? = null
    var repeat: String? = null

    private val subtasks: BaseList<Subtask> = BaseList()

    fun setSubtaskListOnChangeListener(listener: (newList: BaseList<Subtask>) -> Unit) {
        subtasks.setOnChangeListener { listener.invoke(it) }
    }

    fun newSubtask(defaultName: String = "New Subtask"): Int {
        subtasks.apply {
            setNewItemInitializer { id ->
               Subtask(id = id, name = "$defaultName $id", taskId = 0)
            }
            return insertNewItem()
        }
    }

    fun deleteSubtask(subtask: Subtask): Int = subtasks.deleteAndGetIndex(subtask)

    fun updateSubtask(subtask: Subtask): Int = subtasks.updateAndGetIndex(subtask)

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
            val subtasksArray = subtasks.map { it.copy(id = 0, taskId = id) }.toTypedArray()
            repositoryBuilder.subtaskRepository.insertSubtask(*subtasksArray)
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