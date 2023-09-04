package me.lordierclaw.todo2.data.base.model


data class Subtask(
    val id: Int = 0,
    val taskId: Int,
    val name: String,
    var status: Boolean = false
)
