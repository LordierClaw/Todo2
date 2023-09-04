package me.lordierclaw.todo2.screen.task.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import me.lordierclaw.todo2.databinding.DialogDatetimePickerBinding
import me.lordierclaw.todo2.screen.utils.CallbackResult

class DatetimePickerDialogFragment: DialogFragment() {

    private var _binding: DialogDatetimePickerBinding? = null
    private val binding get() = _binding!!

    private var listener: IDatetimePickerListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogDatetimePickerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dialogDatetimePickerCancelBtn.setOnClickListener { dismiss() }
        binding.dialogDatetimePickerDoneBtn.setOnClickListener {
            listener?.onFinish(CallbackResult.SUCCESS, null, null)
            super.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        listener?.onFinish(CallbackResult.FAILED, null, null)
        super.onDismiss(dialog)
    }

    companion object {
        fun newInstance(listener: IDatetimePickerListener) = DatetimePickerDialogFragment().also {
            it.dialog?.setCanceledOnTouchOutside(true)
            it.listener = listener
        }
    }
}