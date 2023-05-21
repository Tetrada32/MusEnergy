package com.gahov.musenergy.feature.favorites

import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(
        contentLayoutID = R.layout.fragment_favorites,
        viewModelClass = FavoritesViewModel::class.java
    ) {
}