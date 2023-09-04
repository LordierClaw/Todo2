package me.lordierclaw.todo2.data.repository

import me.lordierclaw.todo2.data.model.Subtask

interface ISubtaskRepository {
    suspend fun insertSubtask(subtask: Subtask)
    suspend fun updateSubtask(subtask: Subtask)
    suspend fun deleteSubtask(subtask: Subtask)
}