package me.lordierclaw.todo2.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.data.base.repository.ITaskRepository
import me.lordierclaw.todo2.data.local.dao.TaskDao
import me.lordierclaw.todo2.data.local.entity.TaskEntity

class LocalTaskRepository(private val dao: TaskDao): ITaskRepository {
    override suspend fun insertTask(task: Task): Int {
        return dao.insert(TaskEntity.from(task)).toInt()
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

    override fun getAllTask(): LiveData<List<Task>> {
        val taskEntities = dao.getAllTaskEntity()
        return taskEntities.map { list ->
            list.map { it.toTask() }
        }
    }

}