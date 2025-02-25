package com.gahov.musenergy.feature.articles.details

import android.os.Bundle
import android.view.View
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
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
import com.gahov.musenergy.feature.articles.factory.ArticleEntityBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleDetailsFragment :
    BaseFragment<FragmentArticleDetailsBinding, ArticleDetailsViewModel>(
        contentLayoutID = R.layout.fragment_article_details,
        viewModelClass = ArticleDetailsViewModel::class.java
    ) {

    private val args: ArticleDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var articleEntityBuilder: ArticleEntityBuilder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContent()
    }

    private fun setupContent() {
        val articleDetails = args.article
        binding.presenter = viewModel
        binding.article = articleDetails
        setupFavoritesButton()
    }

    private fun setupFavoritesButton() {
        binding.root.findViewById<ComposeView>(R.id.favoriteButton).setContent {
            MaterialTheme {
                FavoriteArticleButton(onClick = {
                    viewModel.onFavoriteButtonClick(
                        articleEntityBuilder.buildDomainArticleFromView(
                            requireContext(),
                            binding.article?.articleData
                        )
                    )
                })
            }
        }
    }

    @Composable
    fun FavoriteArticleButton(onClick: () -> Unit) {
        var isFavorite = binding.article?.articleData?.isFavorite ?: false

        SmallFloatingActionButton(
            onClick = {
                isFavorite = !isFavorite
                onClick()
            },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            if (isFavorite) {
                Icon(Icons.Filled.Favorite, "Add article to favorites")
            } else {
                Icon(Icons.Filled.FavoriteBorder, "Remove article from favorites")
            }
        }
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