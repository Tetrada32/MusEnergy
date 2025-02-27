package com.gahov.musenergy.feature.favorites

import androidx.lifecycle.LiveData
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.articles.params.FavoriteArticleParams
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.usecase.articles.favorites.FetchFavouritesUseCase
import com.gahov.domain.usecase.articles.favorites.UpdateFavouriteUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.arch.lifecycle.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val fetchFavouritesUseCase: FetchFavouritesUseCase,
    private val updateFavouriteUseCase: UpdateFavouriteUseCase,
    private val logger: Logger,
) : BaseViewModel() {

    init {
        loadFavouritesContent()
    }

    private val _loadedContent = SingleLiveEvent<List<ArticleEntity>>()
    val favouritesArticles: LiveData<List<ArticleEntity>>
        get() = _loadedContent

    private fun loadFavouritesContent() {
        launch {
            fetchFavouritesUseCase.execute().collect { result ->
                when (result) {
                    is Either.Left -> onResultFailure(result.failure)
                    is Either.Right -> onResultSuccess(result.success)
                }
            }
        }
    }

    private fun onResultSuccess(favoritesArticles: List<ArticleEntity>) {
        logger.log(message = "Favorites List: fetch result: \n $favoritesArticles")
        _loadedContent.postValue(favoritesArticles)
    }

    private fun onResultFailure(failureResult: Failure) {
        logger.log(message = "Favorites List: fetch Failure: \n $failureResult")
    }

    fun removeFromFavorites(article: ArticleEntity) {
        launch {
            updateFavouriteUseCase.execute(
                FavoriteArticleParams(
                    id = article.id ?: return@launch,
                    newFavoriteStatus = !article.isFavorite
                )
            )
        }
    }
}