package com.gahov.musenergy.feature.articles.list

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleCategoryParams
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.usecase.news.list.LoadArticleListUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.feature.articles.factory.ArticleEntityToModelBuilder
import com.gahov.musenergy.feature.articles.list.command.ArticleListCommand
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.frontpage.presenter.FrontpagePresenter
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val logger: com.gahov.domain.component.logger.Logger,
    private val loadArticleListUseCase: LoadArticleListUseCase
) : BaseViewModel(), FrontpagePresenter {

    private val modelBuilder = ArticleEntityToModelBuilder()

    fun loadContent(category: SearchNewsCategory) {
        launch {
            when (val result = loadArticleListUseCase.execute(ArticleCategoryParams(category))) {
                is Either.Right -> onResultSuccess(articleRawList = result.success)
                is Either.Left -> onResultFailure(result.failure)
            }
        }
    }

    private fun onResultSuccess(articleRawList: List<ArticleEntity>) {
        val formattedList = modelBuilder.create(articleRawList)
        handleCommand(ArticleListCommand.DisplayContent(content = formattedList))
    }

    private fun onResultFailure(failureResult: Failure) {
        logger.log(message = "Failure: \n $failureResult")
    }

    //TODO
    override fun onArticleClick(article: ArticleModel.DefaultArticle) {
//        navigateDirection(FrontpageFragmentDirections.actionFrontpageToArticleDetails(article))
    }
}