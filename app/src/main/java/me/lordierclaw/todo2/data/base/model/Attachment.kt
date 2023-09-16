package me.lordierclaw.todo2.data.base.model

data class Attachment(
    override val id: Int = 0,
    val type: String?,
    val url: String,
    val taskId: Int
): Identifiable
