package me.lordierclaw.todo2.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithTask(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    ) val tasks: List<Task>
)