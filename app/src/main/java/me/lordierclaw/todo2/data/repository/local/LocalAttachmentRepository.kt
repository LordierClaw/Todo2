package me.lordierclaw.todo2.data.repository.local

import me.lordierclaw.todo2.data.dao.AttachmentDao
import me.lordierclaw.todo2.data.model.Attachment
import me.lordierclaw.todo2.data.repository.IAttachmentRepository

class LocalAttachmentRepository(private val dao: AttachmentDao): IAttachmentRepository {
    override suspend fun insert(attachment: Attachment) = dao.insert(attachment)

    override suspend fun update(attachment: Attachment) = dao.update(attachment)

    override suspend fun delete(attachment: Attachment) = dao.delete(attachment)
}