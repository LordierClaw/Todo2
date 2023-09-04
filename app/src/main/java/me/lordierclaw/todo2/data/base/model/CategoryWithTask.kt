package me.lordierclaw.todo2.data.base.model

data class CategoryWithTask(
    val category: Category,
    val tasks: List<Task>
)
