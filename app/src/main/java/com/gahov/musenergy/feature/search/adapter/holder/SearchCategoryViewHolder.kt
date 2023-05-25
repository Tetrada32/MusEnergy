package com.gahov.musenergy.feature.search.adapter.holder

import androidx.databinding.ViewDataBinding
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.databinding.ItemSearchCategoryBinding
import com.gahov.musenergy.feature.search.adapter.model.SearchSectionModel
import com.gahov.musenergy.feature.search.presenter.SearchPresenter

class SearchCategoryViewHolder(
    binding: ViewDataBinding,
    private val presenter: SearchPresenter
) : BaseViewHolder<SearchSectionModel, ItemSearchCategoryBinding>(binding) {

    private lateinit var currentItem: SearchSectionModel.SearchCategorySection

    override fun bindView(position: Int) {
        currentItem = item as SearchSectionModel.SearchCategorySection
        binding.presenter = presenter
        binding.model = currentItem
    }
}