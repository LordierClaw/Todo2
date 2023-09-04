package me.lordierclaw.todo2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import me.lordierclaw.todo2.data.local.entity.SubtaskEntity

@Dao
interface SubtaskDao {
    @Insert(entity = SubtaskEntity::class)
    suspend fun insert(subtaskEntity: SubtaskEntity)

    @Update(entity = SubtaskEntity::class)
    suspend fun update(subtaskEntity: SubtaskEntity)

    @Delete(entity = SubtaskEntity::class)
    suspend fun delete(subtaskEntity: SubtaskEntity)
}