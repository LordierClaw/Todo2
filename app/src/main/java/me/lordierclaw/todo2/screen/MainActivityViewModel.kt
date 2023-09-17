package me.lordierclaw.todo2.screen

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.lordierclaw.todo2.TodoApplication
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder
import me.lordierclaw.todo2.utils.observeOnce

class MainActivityViewModel(private val repositoryBuilder: IRepositoryBuilder) : ViewModel() {

    private var _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val tasks: LiveData<List<Task>> get() = _tasks

    private val _categoryId: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(0)
    }
    val categoryId: LiveData<Int> get() = _categoryId

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repositoryBuilder.taskRepository.updateTask(task)
        }
    }

    fun setCategoryFilter(id: Int, lifecycleOwner: LifecycleOwner) {
        _categoryId.value = id
        if (_categoryId.value == 0) {
            repositoryBuilder.taskRepository.getAllTask().observeOnce(lifecycleOwner) {
                _tasks.value = it
            }
        } else {
            repositoryBuilder.taskRepository.getAllTaskOfCategory(id).observeOnce(lifecycleOwner) {
                _tasks.value = it
            }
        }
    }

    fun getAllCategory(): LiveData<List<Category>> = repositoryBuilder.categoryRepository.getAllCategory()

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val application = this.application as TodoApplication
            return MainActivityViewModel(application.repositoryBuilder) as T
        }
    }
}