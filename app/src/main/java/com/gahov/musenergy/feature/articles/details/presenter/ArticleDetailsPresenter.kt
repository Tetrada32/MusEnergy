package com.gahov.musenergy.feature.articles.details.presenter

import com.gahov.musenergy.feature.articles.model.ArticleModel


interface ArticleDetailsPresenter {

    fun onShareIconClick(article: ArticleModel)

    fun onOpenInBrowserClick(article: ArticleModel)

    fun onFavoriteButtonClick(id: Long, isFavorite: Boolean)
}