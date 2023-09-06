package me.lordierclaw.todo2.screen.task.recyclerview.subtask

import me.lordierclaw.todo2.data.base.model.Subtask

interface ISubtaskListener {
    fun onCheck(subtask: Subtask)

    fun removeButtonOnClick(subtask: Subtask)

    fun afterEditName(subtask: Subtask)
}