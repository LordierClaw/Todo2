package me.lordierclaw.todo2.data.base.model

data class Category(
    override val id: Int = 0,
    val name: String
): Identifiable
