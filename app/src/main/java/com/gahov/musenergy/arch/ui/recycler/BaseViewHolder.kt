package com.gahov.musenergy.arch.ui.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : Any, B : ViewDataBinding>(binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    lateinit var item: T
        private set

    @Suppress("UNCHECKED_CAST")
    val binding: B = binding as B

    fun bindView(position: Int, item: T) {
        this.item = item
        bindView(position)
    }

    abstract fun bindView(position: Int)

    open fun onAttachToWindow() {}

    open fun onDetachFromWindow() {}
}