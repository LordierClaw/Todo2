package me.lordierclaw.todo2.data.repository.local

import androidx.lifecycle.LiveData
import me.lordierclaw.todo2.data.dao.TaskDao
import me.lordierclaw.todo2.data.model.Task
import me.lordierclaw.todo2.data.model.TaskDetail
import me.lordierclaw.todo2.data.repository.ITaskRepository

class LocalTaskRepository(private val dao: TaskDao): ITaskRepository {
    override suspend fun insertTask(task: Task) = dao.insert(task)

    override suspend fun updateTask(task: Task) = dao.update(task)

    override suspend fun deleteTask(task: Task) = dao.delete(task)

    override fun getTask(id: Int): LiveData<Task> = dao.getTask(id)

    override fun getTaskDetail(id: Int): LiveData<TaskDetail> = dao.getTaskDetail(id)
}