package me.lordierclaw.todo2.screen.task.recyclerview.category

import me.lordierclaw.todo2.data.base.model.Category

interface ICategoryListener {
    fun afterEditName(category: Category)
    fun removeOnClick(category: Category)
}