package me.lordierclaw.todo2.screen.task.recyclerview.subtask

import android.view.LayoutInflater
import android.view.ViewGroup
import me.lordierclaw.todo2.data.base.model.Subtask
import me.lordierclaw.todo2.databinding.ViewholderSubtaskBinding
import me.lordierclaw.todo2.utils.recyclerview.BaseAdapter

class SubtaskAdapter(private val listener: ISubtaskListener):
    BaseAdapter<Subtask, ViewholderSubtaskBinding, SubtaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtaskViewHolder {
        val binding: ViewholderSubtaskBinding = ViewholderSubtaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SubtaskViewHolder(binding, listener)
    }
}