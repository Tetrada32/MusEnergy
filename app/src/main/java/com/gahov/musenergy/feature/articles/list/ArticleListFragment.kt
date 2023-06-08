package com.gahov.musenergy.feature.articles.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentArticleListBinding
import com.gahov.musenergy.feature.articles.list.command.ArticleListCommand
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.frontpage.adapter.ArticleListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment :
    BaseFragment<FragmentArticleListBinding, ArticleListViewModel>(
        contentLayoutID = R.layout.fragment_article_list,
        viewModelClass = ArticleListViewModel::class.java
    ) {

    private val args: ArticleListFragmentArgs by navArgs()

    private val frontpageAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter(presenter = viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadContent()
        setupAdapter()
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.isLoading.observe(viewLifecycleOwner, ::showLoadingProgress)
    }

    override fun handleFeatureCommand(command: Command.FeatureCommand) {
        with(command) {
            if (this is ArticleListCommand) {
                when (this) {
                    is ArticleListCommand.DisplayContent -> displayContent(content)
                    is ArticleListCommand.NetworkError -> {}
                }
            } else {
                super.handleFeatureCommand(command)
            }
        }
    }

    private fun loadContent() {
        viewModel.loadContent(args.category)
    }

    private fun setupAdapter() {
        binding.articleListRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.articleListRecycler.adapter = frontpageAdapter
    }

    private fun displayContent(content: List<ArticleModel>) {
        frontpageAdapter.items = content
    }

    private fun showLoadingProgress(isLoading: Boolean) {
        binding.articleListProgress.isVisible = isLoading
    }
}