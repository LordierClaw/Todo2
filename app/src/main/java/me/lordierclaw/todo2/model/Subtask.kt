package me.lordierclaw.todo2.model

data class Subtask(
    val id: Int,
    val taskId: Int,
    val name: String,
    var status: Boolean = false
)
