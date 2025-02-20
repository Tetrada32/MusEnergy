package com.gahov.musenergy.feature.favorites

import androidx.lifecycle.LiveData
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.usecase.news.favouties.LoadFavouritesUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.arch.lifecycle.SingleLiveEvent
import com.gahov.musenergy.feature.articles.factory.ArticleEntityBuilder
import com.gahov.musenergy.feature.articles.model.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favouritesUseCase: LoadFavouritesUseCase,
    private val articleEntityBuilder: Provider<ArticleEntityBuilder>,
    private val logger: Logger,
) : BaseViewModel() {

    private val _loadedContent = SingleLiveEvent<List<ArticleModel>>()
    val favouritesArticles : LiveData<List<ArticleModel>>
        get() = _loadedContent

    init {
        loadFavouritesContent()
    }

    private fun loadFavouritesContent() {
        launch {
            when (val result = favouritesUseCase.execute(param = null)) {
                is Either.Right -> onResultSuccess(articleRawList = result.success)
                is Either.Left -> onResultFailure(result.failure)
            }
        }
    }

    private fun onResultSuccess(articleRawList: List<ArticleEntity>) {
        val formattedList = articleEntityBuilder.get().buildFrontpageList(articleRawList)
        _loadedContent.postValue(formattedList)
    }

    private fun onResultFailure(failureResult: Failure) {
        logger.log(message = "Failure: \n $failureResult")
    }
}