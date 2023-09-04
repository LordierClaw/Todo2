package me.lordierclaw.todo2.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import me.lordierclaw.todo2.data.local.entity.CategoryEntity
import me.lordierclaw.todo2.data.local.entity.CategoryWithTaskEntity

@Dao
interface CategoryDao {
    @Insert(entity = CategoryEntity::class)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Update(entity = CategoryEntity::class)
    suspend fun update(categoryEntity: CategoryEntity)

    @Delete(entity = CategoryEntity::class)
    suspend fun delete(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategoryEntity(id: Int): LiveData<CategoryEntity>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategoryWithTasksEntity(id: Int): LiveData<CategoryWithTaskEntity>

    @Query("SELECT * FROM category")
    fun getAllCategoryEntity(): LiveData<List<CategoryEntity>>
}