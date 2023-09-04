package me.lordierclaw.todo2.data.local.repository

import me.lordierclaw.todo2.data.base.model.Subtask
import me.lordierclaw.todo2.data.local.dao.SubtaskDao
import me.lordierclaw.todo2.data.local.entity.SubtaskEntity
import me.lordierclaw.todo2.data.base.repository.ISubtaskRepository

class LocalSubtaskRepository(private val dao: SubtaskDao): ISubtaskRepository {
    override suspend fun insertSubtask(subtask: Subtask) {
        dao.insert(SubtaskEntity.from(subtask))
    }

    override suspend fun updateSubtask(subtask: Subtask) {
        dao.update(SubtaskEntity.from(subtask))
    }

    override suspend fun deleteSubtask(subtask: Subtask) {
        dao.delete(SubtaskEntity.from(subtask))
    }

}