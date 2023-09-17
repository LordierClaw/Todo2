package me.lordierclaw.todo2.screen.task.recyclerview.category

import android.view.LayoutInflater
import android.view.ViewGroup
import me.lordierclaw.todo2.data.base.model.Category
import me.lordierclaw.todo2.databinding.ViewholderCategoryBinding
import me.lordierclaw.todo2.utils.recyclerview.BaseAdapter

class CategoryAdapter(private val listener: ICategoryListener) :
    BaseAdapter<Category, ViewholderCategoryBinding, CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ViewholderCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding, listener)
    }

}