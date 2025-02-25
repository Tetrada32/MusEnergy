package com.gahov.musenergy.feature.favorites

import androidx.lifecycle.LiveData
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.usecase.articles.favorites.FetchFavouritesUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.arch.lifecycle.SingleLiveEvent
import com.gahov.musenergy.feature.articles.factory.ArticleEntityBuilder
import com.gahov.musenergy.feature.articles.model.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val fetchFavouritesUseCase: FetchFavouritesUseCase,
    private val articleEntityBuilder: Provider<ArticleEntityBuilder>,
    private val logger: Logger,
) : BaseViewModel() {

    private val _loadedContent = SingleLiveEvent<List<ArticleModel>>()
    val favouritesArticles: LiveData<List<ArticleModel>>
        get() = _loadedContent

    fun loadFavouritesContent() {
        launch {
            fetchFavouritesUseCase.execute().collect { result ->
                when (result) {
                    is Either.Left -> onResultFailure(result.failure)
                    is Either.Right -> onResultSuccess(result.success)
                }
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