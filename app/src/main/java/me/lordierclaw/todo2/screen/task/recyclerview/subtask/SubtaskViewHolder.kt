package me.lordierclaw.todo2.screen.task.recyclerview.subtask

import me.lordierclaw.todo2.data.base.model.Subtask
import me.lordierclaw.todo2.databinding.ViewholderSubtaskBinding
import me.lordierclaw.todo2.utils.recyclerview.BaseViewHolder

class SubtaskViewHolder(
    override val binding: ViewholderSubtaskBinding,
    private val listener: ISubtaskListener
) : BaseViewHolder<Subtask, ViewholderSubtaskBinding>(binding) {

    override fun bind(item: Subtask) {
        // Bind View
        binding.subtaskNameTxt.setText(item.name)
        binding.subtaskCheckboxBtn.isChecked = item.status
        // Bind Listener
        binding.subtaskCheckboxBtn.setOnClickListener {
            listener.onCheck(getLatestSubtask(item))
        }
        binding.subtaskRemoveBtn.setOnClickListener {
            listener.removeButtonOnClick(getLatestSubtask(item))
        }
        binding.subtaskNameTxt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                listener.afterEditName(getLatestSubtask(item))
            }
        }
    }

    private fun getLatestSubtask(subtask: Subtask): Subtask {
        return subtask.copy(
            name = binding.subtaskNameTxt.text.toString().trim(),
            status = binding.subtaskCheckboxBtn.isChecked,
        )
    }
}