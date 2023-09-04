package me.lordierclaw.todo2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import me.lordierclaw.todo2.data.model.Task
import me.lordierclaw.todo2.data.model.TaskDetail

@Dao
interface TaskDao {
    @Insert(entity = Task::class)
    suspend fun insert(task: Task)

    @Update(entity = Task::class)
    suspend fun update(task: Task)

    @Delete(entity = Task::class)
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTask(id: Int): LiveData<Task>

    @Transaction
    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskDetail(id: Int): LiveData<TaskDetail>
}