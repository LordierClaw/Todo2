package me.lordierclaw.todo2.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class TaskDetail(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id"
    ) val subtasks: List<Subtask>,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id"
    ) val attachment: List<Attachment>,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    ) val category: Category
)