package me.lordierclaw.todo2.screen.task.taskdetail

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import me.lordierclaw.todo2.R
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.data.base.model.Subtask
import me.lordierclaw.todo2.databinding.FragmentTaskDetailBinding
import me.lordierclaw.todo2.screen.task.dialog.datetimepicker.DatetimePickerDialogFragment
import me.lordierclaw.todo2.screen.task.dialog.datetimepicker.IDatetimePickerListener
import me.lordierclaw.todo2.screen.task.recyclerview.subtask.ISubtaskListener
import me.lordierclaw.todo2.screen.task.recyclerview.subtask.SubtaskAdapter
import me.lordierclaw.todo2.utils.Formatter
import java.util.Date


class TaskDetailFragment : Fragment() {

    companion object {
        const val ARG_TASK_ID = "task_id"
    }

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<TaskDetailViewModel> {
        TaskDetailViewModel.Factory(requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // onBackPress
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            }
        )
    }

    private fun onBackPressed() {
        val navController = findNavController()
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setMessage("Do you want to save these change?")
                .setNeutralButton("Cancel") {dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton("No") {dialog, _ ->
                    navController.popBackStack()
                    dialog.dismiss()
                }
                .setPositiveButton("Yes") {dialog, _ ->
                    saveChange()
                    navController.popBackStack()
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Toolbar
        binding.taskDetailToolbar.setupWithNavController(findNavController())
        binding.taskDetailToolbar.setNavigationOnClickListener { onBackPressed() }
        // Setup task information
        val id: Int = requireArguments().getInt(ARG_TASK_ID)
        viewModel.setupWithTask(id, viewLifecycleOwner).observe(viewLifecycleOwner) {
            setupTaskDetail()
        }
    }

    private fun saveChange() {
        binding.root.clearFocus()
        viewModel.saveChange()
    }

    private fun setupTaskDetail() {
        // Category
        binding.taskDetailCategoryBtn.setOnClickListener {
            showCategoryMenu(binding.taskDetailCategoryBtn)
        }
        viewModel.category.observe(viewLifecycleOwner) {
            binding.taskDetailCategoryBtn.text = it?.name ?: getString(R.string.none)
        }
        // Title
        binding.taskDetailTitleTxt.setText(viewModel.taskName)
        binding.taskDetailTitleTxt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) viewModel.taskName = binding.taskDetailTitleTxt.text.toString().trim()
        }
        // Due date
        bindTaskDueDateAndRepeat()
        binding.taskDetailDueDateBtn.setOnClickListener { pickNewDate() }
        // Repeat
        binding.taskDetailRepeatTxt.text = viewModel.taskRepeat ?: getString(R.string.none)
        binding.taskDetailRepeatBtn.setOnClickListener { pickNewDate() }
        // Note
        binding.taskDetailNoteTxt.setText(viewModel.taskNote ?: getString(R.string.none))
        binding.taskDetailNoteTxt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.taskNote = binding.taskDetailNoteTxt.text.toString().trim()
            }
        }
        // Subtask recycler view
        val subtaskAdapter = SubtaskAdapter(object : ISubtaskListener{
            override fun onCheck(subtask: Subtask) {
                viewModel.updateSubtask(subtask)
            }

            override fun removeButtonOnClick(subtask: Subtask) {
                viewModel.deleteSubtask(subtask)
            }

            override fun afterEditName(subtask: Subtask) {
                viewModel.updateSubtask(subtask)
            }
        })
        viewModel.subtasks.observe(viewLifecycleOwner) {
            subtaskAdapter.setDataWithDiffUtil(it)
        }
        binding.taskDetailSubtaskRcv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = subtaskAdapter
        }
        binding.taskDetailAddSubtaskBtn.setOnClickListener { viewModel.newSubtask() }
        // Attachment
    }

    private fun pickNewDate() {
        DatetimePickerDialogFragment.newInstance(object : IDatetimePickerListener{
            override fun onFinish(result: Int, date: Date?, repeat: String?) {
                viewModel.taskDate = date
                viewModel.taskRepeat = repeat
                bindTaskDueDateAndRepeat()
            }
        }).show(childFragmentManager, "date_picker")
    }

    private fun showCategoryMenu(view: View) {
        val popupMenu = PopupMenu(context, view, Gravity.END)
        popupMenu.menu.add(0, 0, 0, getString(R.string.none))
        var categories: List<Category> = listOf()
        viewModel.getAllCategory().observe(viewLifecycleOwner) { list ->
            categories = list
            list.forEachIndexed { index, category ->
                popupMenu.menu.add(0, category.id, index + 1, category.name)
            }
        }
        popupMenu.setOnMenuItemClickListener {
            if (it.title == getString(R.string.none) && it.itemId == 0) {
                viewModel.setCategory(null)
                true
            } else {
                viewModel.setCategory(categories[it.order-1])
                true
            }

        }
        popupMenu.show()
    }

    fun bindTaskDueDateAndRepeat() {
        binding.taskDetailDueDateTxt.text =
            if (viewModel.taskDate == null)
                getString(R.string.none)
            else
                Formatter.dateToString(viewModel.taskDate!!, Formatter.DateFormat.TIME_AND_DATE)
        binding.taskDetailRepeatTxt.text = viewModel.taskRepeat ?: getString(R.string.none)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}