package me.lordierclaw.todo2.screen.task.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.lordierclaw.todo2.databinding.DialogAddTaskBinding
import me.lordierclaw.todo2.screen.utils.CallbackResult
import java.util.Date

class AddTaskDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogAddTaskBinding? = null
    private val binding get() = _binding!!

    private var listener: IAddTaskListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dialogAddTaskCalendarBtn.setOnClickListener { calendarBtnOnClick() }
        binding.dialogAddTaskAddBtn.setOnClickListener { addTaskBtnOnClick() }
        binding.dialogAddTaskCategoryBtn.setOnClickListener { showCategoryMenu(it) }
    }

    private fun addTaskBtnOnClick() {
        listener?.onFinish(CallbackResult.SUCCESS, null)
        super.dismiss()
    }

    private fun calendarBtnOnClick() {
        val dateDialog = DatetimePickerDialogFragment.newInstance(object : IDatetimePickerListener {
            override fun onFinish(result: Int, date: Date?, repeat: Int?) {
            }
        })
        dateDialog.show(parentFragmentManager, "datetime_picker_dialog")
    }

    private fun showCategoryMenu(view: View) {
        val popup = PopupMenu(context, view, Gravity.TOP)
        // popup.show()
    }

    override fun onDismiss(dialog: DialogInterface) {
        listener?.onFinish(CallbackResult.FAILED, null)
        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(listener: IAddTaskListener) = AddTaskDialogFragment().also {
            it.dialog?.setCanceledOnTouchOutside(true)
            it.listener = listener
        }
    }
}