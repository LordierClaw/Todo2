package me.lordierclaw.todo2.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.data.base.repository.ICategoryRepository
import me.lordierclaw.todo2.data.local.dao.CategoryDao
import me.lordierclaw.todo2.data.local.entity.CategoryEntity

class LocalCategoryRepository(private val dao: CategoryDao): ICategoryRepository {
    override suspend fun insertCategory(category: Category) {
        dao.insert(CategoryEntity.from(category))
    }

    override suspend fun updateCategory(category: Category) {
        dao.update(CategoryEntity.from(category))
    }

    override suspend fun deleteCategory(category: Category) {
        dao.delete(CategoryEntity.from(category))
    }

    override fun getCategory(id: Int): LiveData<Category> {
        return dao.getCategoryEntity(id).map { it.toCategory() }
    }

    override fun getAllCategory(): LiveData<List<Category>> {
        return dao.getAllCategoryEntity().map { list ->
            list.map { it.toCategory() }
        }
    }

}