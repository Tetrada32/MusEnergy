package com.gahov.musenergy.feature.frontpage

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ktx.hideKeyboard
import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentFrontpageBinding
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.frontpage.adapter.FrontpageAdapter
import com.gahov.musenergy.feature.frontpage.command.FrontpageCommand
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrontpageFragment :
    BaseFragment<FragmentFrontpageBinding, FrontpageViewModel>(
        contentLayoutID = R.layout.fragment_frontpage,
        viewModelClass = FrontpageViewModel::class.java
    ) {

    private val frontpageAdapter: FrontpageAdapter by lazy {
        FrontpageAdapter(presenter = viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    override fun handleFeatureCommand(command: Command.FeatureCommand) {
        with(command) {
            if (this is FrontpageCommand) {
                when (this) {
                    is FrontpageCommand.DisplayContent -> displayContent(content)
                    is FrontpageCommand.OnReloadEvent -> {}
                    is FrontpageCommand.OnOpenInBrowser -> {}
                    is FrontpageCommand.NetworkError -> {}
                    FrontpageCommand.HideKeyboard -> hideKeyboard()
                }
            } else {
                super.handleFeatureCommand(command)
            }
        }
    }

    private fun setupAdapter() {
        binding.frontpageRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.frontpageRecycler.adapter = frontpageAdapter
    }

    private fun displayContent(content: List<ArticleModel>) {
        frontpageAdapter.items = content
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.isLoading.observe(viewLifecycleOwner, ::showLoadingProgress)
    }

    private fun showLoadingProgress(isLoading: Boolean) {
        binding.frontpageProgress.isVisible = isLoading
    }
}