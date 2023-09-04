package me.lordierclaw.todo2.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.data.base.model.TaskDetail
import me.lordierclaw.todo2.data.local.dao.TaskDao
import me.lordierclaw.todo2.data.local.entity.TaskEntity
import me.lordierclaw.todo2.data.base.repository.ITaskRepository

class LocalTaskRepository(private val dao: TaskDao): ITaskRepository {
    override suspend fun insertTask(task: Task) {
        dao.insert(TaskEntity.from(task))
    }

    override suspend fun updateTask(task: Task) {
        dao.update(TaskEntity.from(task))
    }

    override suspend fun deleteTask(task: Task) {
        dao.delete(TaskEntity.from(task))
    }

    override fun getTask(id: Int): LiveData<Task> {
        return dao.getTaskEntity(id).map { it.toTask() }
    }

    override fun getTaskDetail(id: Int): LiveData<TaskDetail> {
        return dao.getTaskDetailEntity(id).map { it.toTaskDetail() }
    }

}