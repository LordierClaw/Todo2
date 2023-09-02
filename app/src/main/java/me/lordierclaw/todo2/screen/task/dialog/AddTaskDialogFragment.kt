package me.lordierclaw.todo2.screen.task.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.lordierclaw.todo2.databinding.DialogAddTaskBinding
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
        binding.dialogAddTaskCalendarBtn.setOnClickListener {
            val dateDialog = DatetimePickerDialogFragment.newInstance(object : IDatetimePickerListener {
                override fun onFinish(result: Int, date: Date?, repeat: Int?) {
                }
            })
            dateDialog.show(parentFragmentManager, "datetime_picker_dialog")
        }
        binding.dialogAddTaskAddBtn.setOnClickListener {
            listener?.onFinish(CallbackResult.SUCCESS, null)
            super.dismiss()
        }
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