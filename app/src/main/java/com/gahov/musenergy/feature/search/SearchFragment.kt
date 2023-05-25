package com.gahov.musenergy.feature.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentSearchBinding
import com.gahov.musenergy.feature.search.adapter.SearchListAdapter
import com.gahov.musenergy.feature.search.adapter.decorator.SearchGridSpacingItemDecorator
import com.gahov.musenergy.feature.search.adapter.decorator.SearchGridSpanSizeLookup
import com.gahov.musenergy.feature.search.adapter.model.SearchSectionModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(
        contentLayoutID = R.layout.fragment_search,
        viewModelClass = SearchViewModel::class.java
    ) {

    private val searchAdapter: SearchListAdapter by lazy { SearchListAdapter(presenter = viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadContent()
        setupAdapter()
    }

    override fun setObservers() {
        super.setObservers()
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner, ::showLoadingProgress)
            searchData.observe(viewLifecycleOwner, ::submitList)
        }
    }

    private fun submitList(listItems: List<SearchSectionModel?>?) {
        if (!listItems.isNullOrEmpty()) searchAdapter.submitList(listItems)
    }

    private fun setupAdapter() {
        with(binding.searchList) {
            layoutManager = GridLayoutManager(
                requireContext(),
                CATEGORY_GRID_MAX_ROWS
            ).apply { spanSizeLookup = SearchGridSpanSizeLookup(adapter = searchAdapter) }
            adapter = searchAdapter
            addRecyclerItemDecorator(recyclerView = this)
        }
    }

    private fun addRecyclerItemDecorator(recyclerView: RecyclerView) {
        SearchGridSpacingItemDecorator(
            spanCount = CATEGORY_GRID_MAX_ROWS,
            screenSpacing = resources.getDimensionPixelSize(R.dimen.grid_25),
            itemSpacing = resources.getDimensionPixelSize(R.dimen.grid_20),
            includeEdge = true,
            adapter = searchAdapter
        ).also {
            recyclerView.addItemDecoration(it)
        }
    }

    private fun showLoadingProgress(isLoading: Boolean) {
        with(binding) { searchProgress.isVisible = isLoading }
    }

    companion object {
        private const val CATEGORY_GRID_MAX_ROWS = 2
    }
}