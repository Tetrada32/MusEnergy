package com.gahov.musenergy.feature.articles.presenter

import com.gahov.musenergy.feature.articles.model.ArticleModel


interface ArticleHolderPresenter {

    fun onArticleClick(article: ArticleModel)
}