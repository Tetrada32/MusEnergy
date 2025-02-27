package com.gahov.musenergy.feature.articles.details

import com.gahov.domain.component.logger.Level
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.articles.params.ArticleDetailsParams
import com.gahov.domain.entities.articles.params.FavoriteArticleParams
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.usecase.articles.details.FetchArticleDetailsUseCase
import com.gahov.domain.usecase.articles.favorites.UpdateFavouriteUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.feature.articles.details.command.ArticleDetailsCommand
import com.gahov.musenergy.feature.articles.details.presenter.ArticleDetailsPresenter
import com.gahov.musenergy.feature.articles.factory.ArticleEntityBuilder
import com.gahov.musenergy.feature.articles.model.ArticleModel
import javax.inject.Inject


class ArticleDetailsViewModel @Inject constructor(
    private val logger: Logger,
    private val updateFavouriteUseCase: UpdateFavouriteUseCase,
    private val fetchArticleDetailsUseCase: FetchArticleDetailsUseCase,
    private val articleEntityBuilder: ArticleEntityBuilder
) : BaseViewModel(), ArticleDetailsPresenter {

    fun fetchArticleDetails(articleId: Long) {
        logger.log(message = "ArticleDetailsViewModel: fetchArticleDetails()")

        launch {
            fetchArticleDetailsUseCase.execute(ArticleDetailsParams(articleId)).collect { result ->
                when (result) {
                    is Either.Left -> onErrorResult(result.failure)
                    is Either.Right -> onSuccessResult(result.success)
                }
            }
        }
    }

    private fun onSuccessResult(articleEntity: ArticleEntity) {
        logger.log(message = "ArticleDetailsViewModel: article successfully fetched: $articleEntity")

        handleCommand(
            ArticleDetailsCommand.DisplayContent(
                articleEntityBuilder.buildArticleDetails(articleEntity)
            )
        )
    }

    private fun onErrorResult(failure: Failure) {
        logger.log(
            level = Level.Error,
            message = "ArticleDetailsViewModel Failure: \n $failure"
        )
    }

    override fun onShareIconClick(article: ArticleModel) {
        val url = article.articleData.urlToSource
        handleCommand(ArticleDetailsCommand.ShareArticle(url))
    }

    override fun onOpenInBrowserClick(article: ArticleModel) {
        logger.log(message = "share article click: $article")

        val url = article.articleData.urlToSource
        handleCommand(ArticleDetailsCommand.OnOpenInBrowser(url))
    }

    override fun onFavoriteButtonClick(id: Long, isFavorite: Boolean) {
        logger.log(message = "article favorite click: id $id, isFavorite: $isFavorite")

        launch { updateFavouriteUseCase.execute(param = FavoriteArticleParams(id, isFavorite)) }
    }
}