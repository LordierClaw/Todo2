package me.lordierclaw.todo2.screen.task.recyclerview.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.databinding.ViewholderTaskBinding
import me.lordierclaw.todo2.screen.utils.Formatter

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasks: MutableList<Task> = mutableListOf()

    private var onCheckListener: ITaskOnCheckListener? = null
    private var onClickListener: ITaskOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding: ViewholderTaskBinding = ViewholderTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(binding, onCheckListener, onClickListener)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    fun setData(tasks: List<Task>) {
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            TaskDiffUtilCallback(tasks, this.tasks)
        )
        diffResult.dispatchUpdatesTo(this)
        this.tasks.clear()
        this.tasks.addAll(tasks)
    }

    fun setOnCheckListener(onCheckListener: ITaskOnCheckListener) {
        this.onCheckListener = onCheckListener
    }

    fun setOnClickListener(onClickListener: ITaskOnClickListener) {
        this.onClickListener = onClickListener
    }

    class TaskViewHolder(
        private val binding: ViewholderTaskBinding,
        private val onCheckListener: ITaskOnCheckListener?,
        private val onClickListener: ITaskOnClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            // Bind View
            binding.taskNameTxt.text = task.name
            binding.taskCheckbox.isChecked = task.status
            if (task.dueDate != null) {
                binding.taskDateTxt.text = Formatter.dateToString(task.dueDate, Formatter.DateFormat.SHORTEN_DATE)
                binding.taskTimeTxt.text = Formatter.dateToString(task.dueDate, Formatter.DateFormat.TIME)
                binding.taskDateTxt.visibility = View.VISIBLE
                binding.taskTimeTxt.visibility = View.VISIBLE
            } else {
                binding.taskDateTxt.visibility = View.GONE
                binding.taskTimeTxt.visibility = View.GONE
            }
            binding.taskAlarmIcon.visibility = if (task.reminderAt != null) View.VISIBLE else View.GONE
            binding.taskRepeatIcon.visibility = if (task.repeat != null) View.VISIBLE else View.GONE
            binding.taskNoteIcon.visibility = if (task.notes != null) View.VISIBLE else View.GONE
            // Bind Listener
            binding.taskCheckbox.setOnCheckedChangeListener { _, status ->
                onCheckListener?.onCheck(status, task)
            }
            binding.root.setOnClickListener {
                onClickListener?.onClick(task)
            }
        }
    }
}