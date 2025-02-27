package com.gahov.musenergy.feature.frontpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.usecase.articles.frontpage.LoadFrontpageUseCase
import com.gahov.domain.usecase.articles.frontpage.UpdateFrontpageUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.feature.articles.factory.ArticleEntityBuilder
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.articles.presenter.ArticleHolderPresenter
import com.gahov.musenergy.feature.frontpage.command.FrontpageCommand
import com.gahov.musenergy.feature.frontpage.presenter.FrontpagePresenter
import javax.inject.Inject
import javax.inject.Provider

class FrontpageViewModel @Inject constructor(
    loadFrontpageUseCase: LoadFrontpageUseCase,
    private val updateFrontpageUseCase: UpdateFrontpageUseCase,
    private val logger: Logger,
    private val articleEntityBuilder: Provider<ArticleEntityBuilder>
) : BaseViewModel(), FrontpagePresenter, ArticleHolderPresenter {

    private val _swipeRefreshEventActive by lazy { MutableLiveData(false) }
    val swipeRefreshEventActive: LiveData<Boolean>
        get() = _swipeRefreshEventActive

    init {
        loadContent(loadFrontpageUseCase)
    }

    private fun loadContent(useCase: AsyncUseCase<List<ArticleEntity>>) {
        _swipeRefreshEventActive.postValue(true)
        launch {
            when (val result = useCase.execute(param = null)) {
                is Either.Right -> onResultSuccess(articleRawList = result.success)
                is Either.Left -> onResultFailure(result.failure)
            }
        }
    }

    private fun onResultSuccess(articleRawList: List<ArticleEntity>) {
        val formattedList = articleEntityBuilder.get().buildFrontpageList(articleRawList)
        handleCommand(FrontpageCommand.DisplayContent(content = formattedList))
        _swipeRefreshEventActive.postValue(false)
    }

    fun onRefreshingContentEvent(isLoading: Boolean = true) {
        _swipeRefreshEventActive.postValue(isLoading)
        loadContent(updateFrontpageUseCase)
    }

    private fun onResultFailure(failureResult: Failure) {
        logger.log(message = "Failure: \n $failureResult")
        _swipeRefreshEventActive.postValue(false)
    }

    override fun onArticleClick(article: ArticleModel) {
        navigateDirection(
            FrontpageFragmentDirections.actionFrontpageToArticleDetails(
                article.articleData.itemId ?: 0L
            )
        )
    }

    override fun onStoriesButtonClick() {
        navigateDirection(FrontpageFragmentDirections.actionFrontpageToStories())
    }
}