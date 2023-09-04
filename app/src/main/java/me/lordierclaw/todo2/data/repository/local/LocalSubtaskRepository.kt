package me.lordierclaw.todo2.data.repository.local

import me.lordierclaw.todo2.data.dao.SubtaskDao
import me.lordierclaw.todo2.data.model.Subtask
import me.lordierclaw.todo2.data.repository.ISubtaskRepository

class LocalSubtaskRepository(private val dao: SubtaskDao): ISubtaskRepository {
    override suspend fun insertSubtask(subtask: Subtask) = dao.insert(subtask)

    override suspend fun updateSubtask(subtask: Subtask) = dao.update(subtask)

    override suspend fun deleteSubtask(subtask: Subtask) = dao.delete(subtask)
}