package com.gahov.musenergy.feature.articles.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ktx.getString
import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.musenergy.common.extensions.openInBrowser
import com.gahov.musenergy.common.extensions.shareWithUrl
import com.gahov.musenergy.databinding.FragmentArticleDetailsBinding
import com.gahov.musenergy.feature.articles.details.command.ArticleDetailsCommand
import com.gahov.musenergy.feature.articles.model.ArticleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment :
    BaseFragment<FragmentArticleDetailsBinding, ArticleDetailsViewModel>(
        contentLayoutID = R.layout.fragment_article_details,
        viewModelClass = ArticleDetailsViewModel::class.java
    ) {

    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContent()
    }

    private fun setupContent() {
        val articleDetails = args.article as ArticleModel.DefaultArticle
        binding.presenter = viewModel
        binding.article = articleDetails
    }

    override fun handleFeatureCommand(command: Command.FeatureCommand) {
        with(command) {
            if (this is ArticleDetailsCommand) {
                when (this) {
                    is ArticleDetailsCommand.OnOpenInBrowser -> openInBrowser(sourceUrl)
                    is ArticleDetailsCommand.ShareArticle -> shareArticle(shareUrl)
                }
            } else {
                super.handleFeatureCommand(command)
            }
        }
    }

    private fun shareArticle(url: TextProvider) {
        requireContext().apply {
            (shareWithUrl(
                text = getString(R.string.share_message),
                url = url.getString(this)
            ))
        }
    }

    private fun openInBrowser(url: TextProvider) {
        requireContext().apply {
            openInBrowser(url = url.getString(this))
        }
    }
}