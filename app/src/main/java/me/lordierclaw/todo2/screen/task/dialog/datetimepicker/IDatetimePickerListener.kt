package me.lordierclaw.todo2.screen.task.dialog.datetimepicker

import java.util.Date

interface IDatetimePickerListener {
    fun onFinish(result: Int, date: Date?, repeat: String?)
}