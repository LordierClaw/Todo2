package me.lordierclaw.todo2.data.base.repository

import androidx.lifecycle.LiveData
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.data.base.model.TaskDetail

interface ITaskRepository {
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    fun getTask(id: Int): LiveData<Task>
    fun getAllTask(): LiveData<List<Task>>
    fun getTaskDetail(id: Int): LiveData<TaskDetail>
}