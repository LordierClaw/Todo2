package me.lordierclaw.todo2.data.base.model

data class TaskDetail(
    val task: Task,
    val subtasks: List<Subtask>,
    val attachments: List<Attachment>,
    val category: Category
)
