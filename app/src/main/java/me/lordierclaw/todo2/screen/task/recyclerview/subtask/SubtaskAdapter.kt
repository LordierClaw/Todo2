package me.lordierclaw.todo2.screen.task.recyclerview.subtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.lordierclaw.todo2.data.base.model.Subtask
import me.lordierclaw.todo2.databinding.ViewholderSubtaskBinding

class SubtaskAdapter(private val listener: ISubtaskListener):
    RecyclerView.Adapter<SubtaskAdapter.SubtaskViewHolder>() {

    private var subtasks: List<Subtask> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtaskViewHolder {
        val binding: ViewholderSubtaskBinding = ViewholderSubtaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SubtaskViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = subtasks.size

    override fun onBindViewHolder(holder: SubtaskViewHolder, position: Int) {
        holder.bind(subtasks[position])
    }

    fun setData(subtasks: List<Subtask>) {
        this.subtasks = subtasks
        notifyDataSetChanged()
//        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
//            SubtaskDiffUtilCallback(subtasks, this.subtasks)
//        )
//        this.subtasks = subtasks
//        diffResult.dispatchUpdatesTo(this)
    }

    class SubtaskViewHolder(
        private val binding: ViewholderSubtaskBinding,
        private val listener: ISubtaskListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subtask: Subtask) {
            // Bind View
            binding.subtaskNameTxt.setText(subtask.name)
            binding.subtaskCheckboxBtn.isChecked = subtask.status
            // Bind Listener
            binding.subtaskCheckboxBtn.setOnCheckedChangeListener { _, _ ->
                listener.onCheck(getLatestSubtask(subtask))
            }
            binding.subtaskRemoveBtn.setOnClickListener {
                listener.removeButtonOnClick(getLatestSubtask(subtask))
            }
            binding.subtaskNameTxt.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) {
                    listener.afterEditName(getLatestSubtask(subtask))
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
}