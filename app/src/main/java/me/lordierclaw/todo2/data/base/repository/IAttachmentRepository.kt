package me.lordierclaw.todo2.data.base.repository

import androidx.lifecycle.LiveData
import me.lordierclaw.todo2.data.base.model.Attachment

interface IAttachmentRepository {
    suspend fun insertAttachment(attachment: Attachment)
    suspend fun updateAttachment(attachment: Attachment)
    suspend fun deleteAttachment(attachment: Attachment)
    fun getAttachment(id: Int): LiveData<Attachment>
}