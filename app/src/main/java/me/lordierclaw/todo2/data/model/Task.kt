package me.lordierclaw.todo2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "task", foreignKeys = [
    ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var status: Boolean = false,
    val name: String,
    @ColumnInfo(name = "category_id", index = true) val categoryId: Int?,
    @ColumnInfo(name = "due_date") val dueDate: Date? = null,
    @ColumnInfo(name = "reminder_at") val reminderAt: Int? = null,
    val repeat: String? = null,
    val notes: String? = null,
)
