package me.lordierclaw.todo2.model

data class Attachment(
    val id: Int,
    val type: String,
    val url: String,
    val taskId: Int
)
