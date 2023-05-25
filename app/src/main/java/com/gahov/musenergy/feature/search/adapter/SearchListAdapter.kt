package com.gahov.musenergy.feature.search.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.recycler.BaseRecyclerListAdapter
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.feature.search.adapter.holder.SearchCategoryViewHolder
import com.gahov.musenergy.feature.search.adapter.holder.SearchMainSectionViewHolder
import com.gahov.musenergy.feature.search.adapter.model.SearchSectionModel
import com.gahov.musenergy.feature.search.presenter.SearchPresenter

class SearchListAdapter(
    private val presenter: SearchPresenter,
) : BaseRecyclerListAdapter<SearchSectionModel>(SearchListDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BaseViewHolder<SearchSectionModel, out ViewDataBinding> {
        return when (SearchListViewType.values()[viewType]) {
            SearchListViewType.SEARCH_MAIN_CATEGORY_SECTION -> SearchMainSectionViewHolder(
                binding = inflate(parent, R.layout.item_search_main_section),
                presenter = presenter
            )
            SearchListViewType.SEARCH_CATEGORY_SECTION -> SearchCategoryViewHolder(
                binding = inflate(parent, R.layout.item_search_category),
                presenter = presenter
            )
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).getModelType().ordinal

}