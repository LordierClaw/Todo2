package me.lordierclaw.todo2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import me.lordierclaw.todo2.data.model.Attachment

@Dao
interface AttachmentDao {
    @Insert(entity = Attachment::class)
    suspend fun insert(attachment: Attachment)

    @Update(entity = Attachment::class)
    suspend fun update(attachment: Attachment)

    @Delete(entity = Attachment::class)
    suspend fun delete(attachment: Attachment)
}