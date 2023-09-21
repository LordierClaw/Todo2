package me.lordierclaw.todo2.screen.mine.recyclerview

import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.databinding.ViewholderTaskSimpleBinding
import me.lordierclaw.todo2.utils.Formatter
import me.lordierclaw.todo2.utils.recyclerview.BaseViewHolder

class TaskSimpleViewHolder(binding: ViewholderTaskSimpleBinding) :
    BaseViewHolder<Task, ViewholderTaskSimpleBinding>(binding) {

    override fun bind(item: Task) {
        binding.taskSimpleName.text = item.name
        if (item.dueDate != null) {
            val datetime: String = Formatter.dateToString(item.dueDate, Formatter.DateFormat.SHORTEN_DATE)
            binding.taskSimpleDatetime.text = datetime
        }
    }
}