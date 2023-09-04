package me.lordierclaw.todo2.data.base.model

import java.util.Date

data class Task(
    val id: Int,
    var status: Boolean = false,
    val name: String,
    val categoryId: Int?,
    val dueDate: Date? = null,
    val reminderAt: Int? = null,
    val repeat: String? = null,
    val notes: String? = null,
)