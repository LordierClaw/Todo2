package me.lordierclaw.todo2.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import me.lordierclaw.todo2.data.base.model.Attachment
import me.lordierclaw.todo2.data.base.repository.IAttachmentRepository
import me.lordierclaw.todo2.data.local.dao.AttachmentDao
import me.lordierclaw.todo2.data.local.entity.AttachmentEntity

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

    override fun getAttachment(id: Int): LiveData<Attachment> {
        return dao.getAttachment(id).map { it.toAttachment() }
    }

}