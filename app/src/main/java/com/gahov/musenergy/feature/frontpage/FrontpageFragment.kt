package com.gahov.musenergy.feature.frontpage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isVisible
import com.gahov.architecture.core.ui.view.model.TextProvider
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

    override fun onResume() {
        super.onResume()
        dismissLoaderAfterDelay()
    }

    private fun dismissLoaderAfterDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.dismissLoading()
        }, DELAY_TIME)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logMessage(TextProvider.Text(LOG_MESSAGE))
    }

    companion object {
        private const val LOG_MESSAGE = "FrontpageFragment: This is initial Log message"
        private const val DELAY_TIME = 2000L
    }
}