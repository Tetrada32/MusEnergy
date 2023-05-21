package com.gahov.musenergy.feature.search

import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(
        contentLayoutID = R.layout.fragment_search,
        viewModelClass = SearchViewModel::class.java
    ) {
}