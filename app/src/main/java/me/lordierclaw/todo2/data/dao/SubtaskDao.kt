package me.lordierclaw.todo2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import me.lordierclaw.todo2.data.model.Subtask

@Dao
interface SubtaskDao {
    @Insert(entity = Subtask::class)
    suspend fun insert(subtask: Subtask)

    @Update(entity = Subtask::class)
    suspend fun update(subtask: Subtask)

    @Delete(entity = Subtask::class)
    suspend fun delete(subtask: Subtask)
}