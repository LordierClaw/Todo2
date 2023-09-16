package me.lordierclaw.todo2.utils.list

import me.lordierclaw.todo2.data.base.model.Identifiable

interface IBaseListOnChangeListener<E: Identifiable> {
    fun onChange(newList: BaseList<E>)
}