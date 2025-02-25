package com.gahov.musenergy.feature.articles.details

import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.articles.params.FavoriteArticleParams
import com.gahov.domain.usecase.articles.favorites.AddFavouriteUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.feature.articles.details.command.ArticleDetailsCommand
import com.gahov.musenergy.feature.articles.details.presenter.ArticleDetailsPresenter
import com.gahov.musenergy.feature.articles.model.ArticleModel
import javax.inject.Inject

class ArticleDetailsViewModel @Inject constructor(
    private val logger: Logger,
    private val addFavouriteUseCase: AddFavouriteUseCase
) : BaseViewModel(), ArticleDetailsPresenter {

    override fun onShareIconClick(article: ArticleModel) {
        val url = article.articleData.urlToSource
        handleCommand(ArticleDetailsCommand.ShareArticle(url))
    }

    override fun onOpenInBrowserClick(article: ArticleModel) {
        val url = article.articleData.urlToSource
        handleCommand(ArticleDetailsCommand.OnOpenInBrowser(url))
    }

    override fun onFavoriteButtonClick(article: ArticleEntity) {
        logger.log(message = "article favorite click: $article")
        launch {
            addFavouriteUseCase.execute(param = FavoriteArticleParams(article))
        }
    }
}