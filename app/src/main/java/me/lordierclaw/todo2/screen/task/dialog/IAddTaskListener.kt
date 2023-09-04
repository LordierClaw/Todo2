package me.lordierclaw.todo2.screen.task.dialog

import me.lordierclaw.todo2.data.local.entity.TaskEntity

interface IAddTaskListener {
    fun onFinish(result: Int, task: TaskEntity?)
}