package me.lordierclaw.todo2.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import me.lordierclaw.todo2.data.local.entity.TaskEntity

@Dao
interface TaskDao {
    @Insert(entity = TaskEntity::class)
    suspend fun insert(taskEntity: TaskEntity): Long

    @Update(entity = TaskEntity::class)
    suspend fun update(taskEntity: TaskEntity)

    @Delete(entity = TaskEntity::class)
    suspend fun delete(taskEntity: TaskEntity)

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskEntity(id: Int): LiveData<TaskEntity>

    @Query("SELECT * FROM task")
    fun getAllTaskEntity(): LiveData<List<TaskEntity>>
}