package me.lordierclaw.todo2.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import me.lordierclaw.todo2.data.base.model.TaskDetail

data class TaskDetailEntity(
    @Embedded val taskEntity: TaskEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id"
    ) val subtaskEntities: List<SubtaskEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id"
    ) val attachmentEntities: List<AttachmentEntity>,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    ) val categoryEntity: CategoryEntity
) {
    companion object {
        fun from(taskDetail: TaskDetail): TaskDetailEntity {
            return TaskDetailEntity(
                taskEntity = TaskEntity.from(taskDetail.task),
                subtaskEntities = taskDetail.subtasks.map { SubtaskEntity.from(it) },
                attachmentEntities = taskDetail.attachments.map { AttachmentEntity.from(it) },
                categoryEntity = CategoryEntity.from(taskDetail.category)
            )
        }
    }

    fun toTaskDetail(): TaskDetail {
        return TaskDetail(
            task = this.taskEntity.toTask(),
            subtasks = this.subtaskEntities.map { it.toSubtask() },
            attachments = this.attachmentEntities.map { it.toAttachment() },
            category = this.categoryEntity.toCategory()
        )
    }
}