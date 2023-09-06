package me.lordierclaw.todo2.data.base.repository

import androidx.lifecycle.LiveData
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.data.base.model.CategoryWithTask

interface ICategoryRepository {
    suspend fun insertCategory(category: Category)
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    fun getCategory(id: Int): LiveData<Category>
    fun getCategoryWithTasks(id: Int): LiveData<CategoryWithTask>
    fun getAllCategory(): LiveData<List<Category>>
}