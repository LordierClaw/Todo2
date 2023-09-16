package me.lordierclaw.todo2.utils.list

interface INewItemInitializer<E> {
    fun init(id: Int): E
}