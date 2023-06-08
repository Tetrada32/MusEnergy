package com.gahov.musenergy.feature.articles.list

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleCategoryParams
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.usecase.news.list.LoadArticleListUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.feature.articles.factory.ArticleEntityBuilder
import com.gahov.musenergy.feature.articles.list.command.ArticleListCommand
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.articles.presenter.ArticleHolderPresenter
import javax.inject.Inject
import javax.inject.Provider

class ArticleListViewModel @Inject constructor(
    private val logger: com.gahov.domain.component.logger.Logger,
    private val loadArticleListUseCase: LoadArticleListUseCase,
    private val articleEntityBuilder: Provider<ArticleEntityBuilder>,
) : BaseViewModel(), ArticleHolderPresenter {

    fun loadContent(category: SearchNewsCategory) {
        launch {
            when (val result = loadArticleListUseCase.execute(ArticleCategoryParams(category))) {
                is Either.Right -> onResultSuccess(
                    articleRawList = result.success,
                    category = category
                )
                is Either.Left -> onResultFailure(result.failure)
            }
        }
    }

    private fun onResultSuccess(articleRawList: List<ArticleEntity>, category: SearchNewsCategory) {
        val formattedList = articleEntityBuilder.get().buildCategoriesList(
            articleEntityItems = articleRawList,
            category = category
        )
        handleCommand(ArticleListCommand.DisplayContent(content = formattedList))
    }

    private fun onResultFailure(failureResult: Failure) {
        logger.log(message = "Failure: \n $failureResult")
    }

    //TODO
    override fun onArticleClick(article: ArticleModel) {
//        navigateDirection(FrontpageFragmentDirections.actionFrontpageToArticleDetails(article))
    }
}