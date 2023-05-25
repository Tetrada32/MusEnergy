package com.gahov.musenergy.feature.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gahov.musenergy.feature.search.adapter.model.SearchSectionModel

class SearchListDiffCallback : DiffUtil.ItemCallback<SearchSectionModel>() {
    override fun areItemsTheSame(
        oldItem: SearchSectionModel,
        newItem: SearchSectionModel
    ): Boolean {
        return oldItem.areItemsSame(newItem)
    }

    override fun areContentsTheSame(
        oldItem: SearchSectionModel,
        newItem: SearchSectionModel
    ): Boolean {
        return oldItem == newItem
    }
}