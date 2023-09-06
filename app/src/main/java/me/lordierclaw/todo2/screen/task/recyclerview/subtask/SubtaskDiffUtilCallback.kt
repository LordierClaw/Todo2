package me.lordierclaw.todo2.screen.task.recyclerview.subtask

import androidx.recyclerview.widget.DiffUtil
import me.lordierclaw.todo2.data.base.model.Subtask

class SubtaskDiffUtilCallback(
    private val newList: List<Subtask>,
    private val oldList: List<Subtask>
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