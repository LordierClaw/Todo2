package me.lordierclaw.todo2.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import me.lordierclaw.todo2.data.base.model.CategoryWithTask

data class CategoryWithTaskEntity(
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    ) val taskEntities: List<TaskEntity>
) {
    companion object {
        fun from(categoryWithTask: CategoryWithTask): CategoryWithTaskEntity {
            return CategoryWithTaskEntity(
                categoryEntity = CategoryEntity.from(categoryWithTask.category),
                taskEntities = categoryWithTask.tasks.map { TaskEntity.from(it) }
            )
        }
    }

    fun toCategoryWithTask(): CategoryWithTask {
        return CategoryWithTask(
            category = this.categoryEntity.toCategory(),
            tasks = this.taskEntities.map { it.toTask() }
        )
    }
}