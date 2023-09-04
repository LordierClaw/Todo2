package me.lordierclaw.todo2.data.repository

import me.lordierclaw.todo2.data.model.Attachment

interface IAttachmentRepository {
    suspend fun insert(attachment: Attachment)
    suspend fun update(attachment: Attachment)
    suspend fun delete(attachment: Attachment)
}