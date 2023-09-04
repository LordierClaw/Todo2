package me.lordierclaw.todo2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "attachment", foreignKeys = [
    ForeignKey(
        entity = Task::class,
        parentColumns = ["id"],
        childColumns = ["task_id"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Attachment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String?,
    val url: String,
    @ColumnInfo(name = "task_id", index = true) val taskId: Int
)
