package com.gahov.musenergy.feature.favorites

import androidx.lifecycle.LiveData
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.usecase.articles.favorites.FetchFavouritesUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.arch.lifecycle.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val fetchFavouritesUseCase: FetchFavouritesUseCase,
    private val logger: Logger,
) : BaseViewModel() {

    companion object {
        //TODO do not keep it here :D
        val placeholderImage =
            "https://www.roadiemusic.com/blog/wp-content/uploads/2020/02/Is-Rock-Music-Dead.png"
    }

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
        //TODO
    }
}