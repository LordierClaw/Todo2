package me.lordierclaw.todo2.screen.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import me.lordierclaw.todo2.R
import me.lordierclaw.todo2.databinding.FragmentAllTaskBinding
import me.lordierclaw.todo2.screen.task.recyclerview.TaskAdapter

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
        viewModel.tasks.observe(viewLifecycleOwner) { taskAdapter.setData(it) }
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