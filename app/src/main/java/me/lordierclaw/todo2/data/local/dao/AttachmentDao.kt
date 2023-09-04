package me.lordierclaw.todo2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import me.lordierclaw.todo2.data.local.entity.AttachmentEntity

@Dao
interface AttachmentDao {
    @Insert(entity = AttachmentEntity::class)
    suspend fun insert(attachmentEntity: AttachmentEntity)

    @Update(entity = AttachmentEntity::class)
    suspend fun update(attachmentEntity: AttachmentEntity)

    @Delete(entity = AttachmentEntity::class)
    suspend fun delete(attachmentEntity: AttachmentEntity)
}