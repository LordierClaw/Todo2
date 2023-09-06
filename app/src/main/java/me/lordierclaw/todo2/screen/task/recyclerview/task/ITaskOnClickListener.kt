package me.lordierclaw.todo2.screen.task.recyclerview.task

import me.lordierclaw.todo2.data.base.model.Task

interface ITaskOnClickListener {
    fun onClick(task: Task)
}