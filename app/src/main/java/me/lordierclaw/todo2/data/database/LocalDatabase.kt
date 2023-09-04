package me.lordierclaw.todo2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.lordierclaw.todo2.data.dao.AttachmentDao
import me.lordierclaw.todo2.data.dao.CategoryDao
import me.lordierclaw.todo2.data.dao.SubtaskDao
import me.lordierclaw.todo2.data.dao.TaskDao
import me.lordierclaw.todo2.data.model.Attachment
import me.lordierclaw.todo2.data.model.Category
import me.lordierclaw.todo2.data.model.Subtask
import me.lordierclaw.todo2.data.model.Task

@Database(
    entities = [
        Task::class,
        Subtask::class,
        Category::class,
        Attachment::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subtaskDao(): SubtaskDao
    abstract fun categoryDao(): CategoryDao
    abstract fun attachmentDao(): AttachmentDao

    companion object {
        @Volatile
        private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, LocalDatabase::class.java, "todo_database")
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}