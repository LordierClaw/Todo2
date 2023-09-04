package me.lordierclaw.todo2.data.base.repository

import androidx.lifecycle.LiveData
import me.lordierclaw.todo2.data.base.model.Subtask

interface ISubtaskRepository {
    suspend fun insertSubtask(subtask: Subtask)
    suspend fun updateSubtask(subtask: Subtask)
    suspend fun deleteSubtask(subtask: Subtask)
    fun getSubtask(id: Int): LiveData<Subtask>
}