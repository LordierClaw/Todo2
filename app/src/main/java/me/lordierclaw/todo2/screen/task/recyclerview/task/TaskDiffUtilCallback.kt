package me.lordierclaw.todo2.screen.task.recyclerview.task

import androidx.recyclerview.widget.DiffUtil
import me.lordierclaw.todo2.data.base.model.Task

class TaskDiffUtilCallback(
    private val newList: List<Task>,
    private val oldList: List<Task>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}