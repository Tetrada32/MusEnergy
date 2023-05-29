package com.gahov.musenergy.feature.frontpage.presenter

import com.gahov.musenergy.feature.articles.model.ArticleModel


interface FrontpagePresenter {

    fun onArticleClick(article: ArticleModel.DefaultArticle)
}