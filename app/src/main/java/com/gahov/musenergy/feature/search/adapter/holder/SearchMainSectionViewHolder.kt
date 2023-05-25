package com.gahov.musenergy.feature.search.adapter.holder

import androidx.databinding.ViewDataBinding
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.databinding.ItemSearchMainSectionBinding
import com.gahov.musenergy.feature.search.adapter.model.SearchSectionModel
import com.gahov.musenergy.feature.search.presenter.SearchPresenter

class SearchMainSectionViewHolder(
    binding: ViewDataBinding,
    private val presenter: SearchPresenter
) : BaseViewHolder<SearchSectionModel, ItemSearchMainSectionBinding>(binding) {

    private lateinit var currentItem: SearchSectionModel.SearchMainSection

    override fun bindView(position: Int) {
        currentItem = item as SearchSectionModel.SearchMainSection
        binding.presenter = presenter
        binding.model = currentItem
    }
}