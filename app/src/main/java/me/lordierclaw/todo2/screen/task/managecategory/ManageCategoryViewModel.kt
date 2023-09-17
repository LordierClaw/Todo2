package me.lordierclaw.todo2.screen.task.managecategory

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.lordierclaw.todo2.TodoApplication
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder

class ManageCategoryViewModel(private val repositoryBuilder: IRepositoryBuilder): ViewModel() {

    fun getAllCategory(): LiveData<List<Category>> = repositoryBuilder.categoryRepository.getAllCategory()

    fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryBuilder.categoryRepository.updateCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryBuilder.categoryRepository.deleteCategory(category)
        }
    }

    fun newCategory(defaultName: String = "New Category") {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryBuilder.categoryRepository.insertCategory(
                Category(name = defaultName)
            )
        }
    }

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val application = this.application as TodoApplication
            return ManageCategoryViewModel(application.repositoryBuilder) as T
        }
    }
}