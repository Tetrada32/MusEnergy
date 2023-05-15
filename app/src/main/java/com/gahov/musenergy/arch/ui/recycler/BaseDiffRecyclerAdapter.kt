package com.gahov.musenergy.arch.ui.recycler

import com.gahov.musenergy.arch.ui.view.ktx.autoNotify
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

abstract class BaseDiffRecyclerAdapter<T : Any> : BaseRecyclerAdapter<T>() {

    override var items: List<T> by Delegates.observable(emptyList(), ::onItemChange)

    private fun onItemChange(prop: KProperty<*>, old: List<T>, new: List<T>) =
        autoNotify(old, new, ::compare)

    abstract fun compare(oldItem: T, newItem: T): Boolean
}
