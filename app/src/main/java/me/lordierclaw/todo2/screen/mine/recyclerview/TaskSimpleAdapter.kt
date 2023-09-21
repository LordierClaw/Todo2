package me.lordierclaw.todo2.screen.mine.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.databinding.ViewholderTaskSimpleBinding
import me.lordierclaw.todo2.utils.recyclerview.BaseAdapter

class TaskSimpleAdapter: BaseAdapter<Task, ViewholderTaskSimpleBinding, TaskSimpleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskSimpleViewHolder {
        val binding = ViewholderTaskSimpleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskSimpleViewHolder(binding)
    }
}