package me.lordierclaw.todo2.screen.task.dialog

import me.lordierclaw.todo2.data.model.Task

interface IAddTaskListener {
    fun onFinish(result: Int, task: Task?)
}