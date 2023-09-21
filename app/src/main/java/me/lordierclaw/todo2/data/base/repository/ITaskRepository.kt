package me.lordierclaw.todo2.data.base.repository

import androidx.lifecycle.LiveData
import me.lordierclaw.todo2.data.base.model.CategoryCount
import me.lordierclaw.todo2.data.base.model.Task

interface ITaskRepository {
    suspend fun insertTask(task: Task): Int
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteTask(id: Int)
    fun getTask(id: Int): LiveData<Task>
    fun getAllTask(): LiveData<List<Task>>
    fun getAllTaskOfCategory(categoryId: Int): LiveData<List<Task>>
    fun getAllTaskContainsTitle(keyword: String): LiveData<List<Task>>
    fun getAllTaskInTimeRange(startTime: Long, endTime: Long): LiveData<List<Task>>
    fun getTaskCountByStatus(status: Boolean): LiveData<Int>
    fun getCategoryCounts(): LiveData<List<CategoryCount>>
}