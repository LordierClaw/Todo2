package me.lordierclaw.todo2.screen.task.alltask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import me.lordierclaw.todo2.R
import me.lordierclaw.todo2.data.base.model.Task
import me.lordierclaw.todo2.databinding.FragmentAllTaskBinding
import me.lordierclaw.todo2.screen.task.recyclerview.task.ITaskOnCheckListener
import me.lordierclaw.todo2.screen.task.recyclerview.task.ITaskOnClickListener
import me.lordierclaw.todo2.screen.task.recyclerview.task.TaskAdapter
import me.lordierclaw.todo2.screen.task.taskdetail.TaskDetailFragment

class AllTaskFragment : Fragment() {

    private var _binding: FragmentAllTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AllTaskViewModel> { AllTaskViewModel.Factory(requireActivity().application) }

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.allTaskMenuBtn.setOnClickListener { showOverflowMenu(it, R.menu.overflow_menu) }
        taskAdapter = TaskAdapter()
        binding.allTaskRcv.layoutManager = LinearLayoutManager(context)
        binding.allTaskRcv.adapter = taskAdapter
        taskAdapter.setOnCheckListener(object : ITaskOnCheckListener {
            override fun onCheck(status: Boolean, task: Task) {
                viewModel.updateTask(task.also { it.status = status })
            }
        })
        taskAdapter.setOnClickListener(object : ITaskOnClickListener {
            override fun onClick(task: Task) {
                showTaskDetail(task)
            }
        })
        viewModel.tasks.observe(viewLifecycleOwner) { taskAdapter.setDataWithDiffUtil(it) }
    }

    private fun showTaskDetail(task: Task) {
        val navController = findNavController()
        navController.navigate(R.id.action_allTaskFragment_to_taskDetailFragment, bundleOf(
            TaskDetailFragment.ARG_TASK_ID to task.id
        ))
    }

    private fun showOverflowMenu(view: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(context, view)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener {
            Toast.makeText(context, "This feature is not available", Toast.LENGTH_SHORT).show()
            true
        }
        popup.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}