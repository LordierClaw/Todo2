package me.lordierclaw.todo2.screen.task.taskdetail

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.lordierclaw.todo2.TodoApplication
import me.lordierclaw.todo2.data.base.model.Attachment
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.data.base.model.Subtask
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder
import me.lordierclaw.todo2.utils.observeOnce
import java.util.Date

class TaskDetailViewModel(private val repositoryBuilder: IRepositoryBuilder): ViewModel() {
    private var taskId: Int = -1
    var taskStatus: Boolean = false
    private var categoryId: Int? = null
    lateinit var taskName: String
    var taskDate: Date? = null
    var taskRepeat: String? = null
    var taskNote: String? = null
    val subtasks: LiveData<List<Subtask>>
        get() = repositoryBuilder.subtaskRepository.getAllSubtaskOfTask(taskId)

    private val _category: MutableLiveData<Category?> = MutableLiveData()

    val category: LiveData<Category?>
        get() = _category
    lateinit var attachments: List<Attachment>

    fun setupWithTask(id: Int, lifecycleOwner: LifecycleOwner): LiveData<Task> {
        taskId = id
        return repositoryBuilder.taskRepository.getTask(taskId).apply {
            observeOnce(lifecycleOwner) {
                taskStatus = it.status
                taskName = it.name
                categoryId = it.categoryId
                observeCategoryLiveData(lifecycleOwner)
                taskDate = it.dueDate
                taskRepeat = it.repeat
                taskNote = it.notes
            }
        }
    }

    private fun observeCategoryLiveData(lifecycleOwner: LifecycleOwner) {
        categoryId?.let { id ->
            repositoryBuilder.categoryRepository.getCategory(id).observeOnce(lifecycleOwner) {
                _category.value = it
            }
        }
    }

    fun saveChange() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryBuilder.taskRepository.updateTask(
                Task(
                    id = taskId,
                    status = taskStatus,
                    name = taskName,
                    dueDate = taskDate,
                    categoryId = categoryId,
                    repeat = taskRepeat,
                    notes = taskNote
                )
            )
        }
    }

    fun newSubtask(defaultName: String = "New Subtask") {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryBuilder.subtaskRepository.insertSubtask(
                Subtask(
                    taskId = taskId,
                    name = defaultName
                )
            )
        }
    }

    fun updateSubtask(subtask: Subtask) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryBuilder.subtaskRepository.updateSubtask(subtask)
        }
    }

    fun deleteSubtask(subtask: Subtask) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryBuilder.subtaskRepository.deleteSubtask(subtask)
        }
    }

    fun setCategory(category: Category?) {
        _category.value = category
        categoryId = category?.id
    }

    fun deleteThisTask() {
        if (taskId == -1) return
        viewModelScope.launch(Dispatchers.IO) {
            repositoryBuilder.taskRepository.deleteTask(taskId)
        }
    }

    fun getAllCategory() : LiveData<List<Category>> = repositoryBuilder.categoryRepository.getAllCategory()

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val application = this.application as TodoApplication
            return TaskDetailViewModel(application.repositoryBuilder) as T
        }
    }
}