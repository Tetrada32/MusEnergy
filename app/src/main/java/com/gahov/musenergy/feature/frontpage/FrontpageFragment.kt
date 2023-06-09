package com.gahov.musenergy.feature.frontpage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentFrontpageBinding
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.frontpage.adapter.ArticleListAdapter
import com.gahov.musenergy.feature.frontpage.command.FrontpageCommand
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrontpageFragment :
    BaseFragment<FragmentFrontpageBinding, FrontpageViewModel>(
        contentLayoutID = R.layout.fragment_frontpage,
        viewModelClass = FrontpageViewModel::class.java
    ) {

    override val isBottomNavigationVisible: Boolean = true

    private val frontpageAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter(presenter = viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.presenter = viewModel

        setupAdapter()
    }

    override fun handleFeatureCommand(command: Command.FeatureCommand) {
        with(command) {
            if (this is FrontpageCommand) {
                when (this) {
                    is FrontpageCommand.DisplayContent -> displayContent(content)
                    is FrontpageCommand.NetworkError -> {}
                }
            } else {
                super.handleFeatureCommand(command)
            }
        }
    }

    private fun setupAdapter() {
        binding.frontpageRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.frontpageRecycler.adapter = frontpageAdapter
        setupSwipeToRefresh()
    }

    private fun setupSwipeToRefresh() {
        binding.frontpageSwipeLayout.setOnRefreshListener {
            viewModel.onRefreshingContentEvent(isLoading = true)
        }
    }

    private fun displayContent(content: List<ArticleModel>) {
        frontpageAdapter.items = content
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.swipeRefreshEventActive.observe(viewLifecycleOwner, ::onRefreshStateChanged)
    }

    private fun onRefreshStateChanged(isRefreshing: Boolean) {
        binding.frontpageSwipeLayout.isRefreshing = isRefreshing
    }
}