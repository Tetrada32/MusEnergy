package com.gahov.musenergy.feature.frontpage

import androidx.core.view.isVisible
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentFrontpageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrontpageFragment :
    BaseFragment<FragmentFrontpageBinding, FrontpageViewModel>(
        contentLayoutID = R.layout.fragment_frontpage,
        viewModelClass = FrontpageViewModel::class.java
    ) {

    override fun setObservers() {
        super.setObservers()
        viewModel.isLoading.observe(viewLifecycleOwner, ::showLoadingProgress)
    }

    private fun showLoadingProgress(isLoading: Boolean) {
        binding.frontpageProgress.isVisible = isLoading
    }
}