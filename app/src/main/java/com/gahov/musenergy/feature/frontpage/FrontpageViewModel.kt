package com.gahov.musenergy.feature.frontpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.usecase.news.frontpage.LoadFrontpageUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.feature.articles.factory.ArticleEntityToModelBuilder
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.frontpage.command.FrontpageCommand
import com.gahov.musenergy.feature.frontpage.presenter.FrontpagePresenter
import javax.inject.Inject

class FrontpageViewModel @Inject constructor(
    private val loadFrontpageUseCase: LoadFrontpageUseCase,
    private val logger: com.gahov.domain.component.logger.Logger
) : BaseViewModel(), FrontpagePresenter {

    private val _swipeRefreshEventActive by lazy { MutableLiveData(false) }
    val swipeRefreshEventActive: LiveData<Boolean>
        get() = _swipeRefreshEventActive

    private val modelBuilder = ArticleEntityToModelBuilder()

    init {
        onRefreshingContentEvent()
    }

    private fun loadFrontpageContent() {
        launch {
            when (val result = loadFrontpageUseCase.execute(param = null)) {
                is Either.Right -> onResultSuccess(articleRawList = result.success)
                is Either.Left -> onResultFailure(result.failure)
            }
        }
    }

    private fun onResultSuccess(articleRawList: List<ArticleEntity>) {
        val formattedList = modelBuilder.create(articleRawList)
        handleCommand(FrontpageCommand.DisplayContent(content = formattedList))
        _swipeRefreshEventActive.postValue(false)
    }

    fun onRefreshingContentEvent(isLoading: Boolean = true) {
        _swipeRefreshEventActive.postValue(isLoading)
        loadFrontpageContent()
    }

    private fun onResultFailure(failureResult: Failure) {
        logger.log(message = "Failure: \n $failureResult")
        _swipeRefreshEventActive.postValue(false)
    }

    override fun onArticleClick(article: ArticleModel.DefaultArticle) {
        navigateDirection(FrontpageFragmentDirections.actionFrontpageToArticleDetails(article))
    }
}