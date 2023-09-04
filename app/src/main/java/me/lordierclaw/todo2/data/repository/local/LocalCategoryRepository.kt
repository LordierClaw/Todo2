package me.lordierclaw.todo2.data.repository.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.lordierclaw.todo2.data.dao.CategoryDao
import me.lordierclaw.todo2.data.model.Category
import me.lordierclaw.todo2.data.model.CategoryWithTask
import me.lordierclaw.todo2.data.repository.ICategoryRepository

class LocalCategoryRepository(private val dao: CategoryDao): ICategoryRepository {
    override suspend fun insertCategory(category: Category) = dao.insert(category)

    override suspend fun updateCategory(category: Category) = dao.update(category)

    override suspend fun deleteCategory(category: Category) = dao.delete(category)

    override fun getCategory(id: Int): LiveData<Category> = dao.getCategory(id)

    override fun getCategoryWithTasks(id: Int): LiveData<CategoryWithTask> =
        MutableLiveData<CategoryWithTask>()

    override fun getAllCategory(): LiveData<List<Category>> = dao.getAllCategory()
}