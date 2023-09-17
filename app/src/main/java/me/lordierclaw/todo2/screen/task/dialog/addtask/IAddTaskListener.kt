package me.lordierclaw.todo2.screen.task.dialog.addtask

interface IAddTaskListener {
    fun onFinish(result: Int, categoryId: Int?)
}