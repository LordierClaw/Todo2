package me.lordierclaw.todo2.data.local.repository

import me.lordierclaw.todo2.data.base.model.Attachment
import me.lordierclaw.todo2.data.local.dao.AttachmentDao
import me.lordierclaw.todo2.data.local.entity.AttachmentEntity
import me.lordierclaw.todo2.data.base.repository.IAttachmentRepository

class LocalAttachmentRepository(private val dao: AttachmentDao): IAttachmentRepository {
    override suspend fun insertAttachment(attachment: Attachment) {
        dao.insert(AttachmentEntity.from(attachment))
    }

    override suspend fun updateAttachment(attachment: Attachment) {
        dao.update(AttachmentEntity.from(attachment))
    }

    override suspend fun deleteAttachment(attachment: Attachment) {
        dao.delete(AttachmentEntity.from(attachment))
    }

}