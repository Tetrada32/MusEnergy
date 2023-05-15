package com.gahov.musenergy.arch.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

@SuppressWarnings(value = ["unused"])
abstract class BaseRecyclerAdapter<T : Any>(open var items: List<T> = arrayListOf()) :
    RecyclerView.Adapter<BaseViewHolder<T, out ViewDataBinding>>() {

    fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemCount() = items.size

    protected fun inflate(parent: ViewGroup, @LayoutRes contentLayoutID: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), contentLayoutID, parent, false
        )
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<T, out ViewDataBinding>) {
        holder.onAttachToWindow()
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<T, out ViewDataBinding>) {
        holder.onDetachFromWindow()
        super.onViewDetachedFromWindow(holder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, out ViewDataBinding>, position: Int) {
        holder.bindView(position, getItem(position))
    }
}
