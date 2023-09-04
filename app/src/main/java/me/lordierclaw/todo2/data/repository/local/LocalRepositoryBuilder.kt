package me.lordierclaw.todo2.data.repository.local

import android.content.Context
import me.lordierclaw.todo2.data.database.LocalDatabase
import me.lordierclaw.todo2.data.repository.IAttachmentRepository
import me.lordierclaw.todo2.data.repository.ICategoryRepository
import me.lordierclaw.todo2.data.repository.IRepositoryBuilder
import me.lordierclaw.todo2.data.repository.ISubtaskRepository
import me.lordierclaw.todo2.data.repository.ITaskRepository

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