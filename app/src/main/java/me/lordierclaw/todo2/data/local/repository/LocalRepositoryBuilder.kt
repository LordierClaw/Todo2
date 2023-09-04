package me.lordierclaw.todo2.data.local.repository

import android.content.Context
import me.lordierclaw.todo2.data.base.repository.IAttachmentRepository
import me.lordierclaw.todo2.data.base.repository.ICategoryRepository
import me.lordierclaw.todo2.data.base.repository.IRepositoryBuilder
import me.lordierclaw.todo2.data.base.repository.ISubtaskRepository
import me.lordierclaw.todo2.data.base.repository.ITaskRepository
import me.lordierclaw.todo2.data.local.LocalDatabase

class LocalRepositoryBuilder(private val context: Context): IRepositoryBuilder {
    override val taskRepository: ITaskRepository
        get() = LocalTaskRepository(LocalDatabase.getInstance(context).taskDao())
    override val subtaskRepository: ISubtaskRepository
        get() = LocalSubtaskRepository(LocalDatabase.getInstance(context).subtaskDao())
    override val categoryRepository: ICategoryRepository
        get() = LocalCategoryRepository(LocalDatabase.getInstance(context).categoryDao())
    override val attachmentRepository: IAttachmentRepository
        get() = LocalAttachmentRepository(LocalDatabase.getInstance(context).attachmentDao())
}