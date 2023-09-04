package me.lordierclaw.todo2.data.base.repository

interface IRepositoryBuilder {
    val taskRepository: ITaskRepository
    val subtaskRepository: ISubtaskRepository
    val categoryRepository: ICategoryRepository
    val attachmentRepository: IAttachmentRepository
}