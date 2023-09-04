package me.lordierclaw.todo2.data.repository

import androidx.lifecycle.LiveData
import me.lordierclaw.todo2.data.model.Task
import me.lordierclaw.todo2.data.model.TaskDetail

interface ITaskRepository {
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    fun getTask(id: Int): LiveData<Task>
    fun getTaskDetail(id: Int): LiveData<TaskDetail>
}