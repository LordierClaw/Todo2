package me.lordierclaw.todo2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import me.lordierclaw.todo2.data.model.Category
import me.lordierclaw.todo2.data.model.CategoryWithTask

@Dao
interface CategoryDao {
    @Insert(entity = Category::class)
    suspend fun insert(category: Category)

    @Update(entity = Category::class)
    suspend fun update(category: Category)

    @Delete(entity = Category::class)
    suspend fun delete(category: Category)

    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategory(id: Int): LiveData<Category>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategoryWithTasks(id: Int): LiveData<CategoryWithTask>

    @Query("SELECT * FROM category")
    fun getAllCategory(): LiveData<List<Category>>
}