package com.gahov.musenergy.feature.articles.list.presenter

import com.gahov.musenergy.feature.articles.model.ArticleModel

//TODO use or remove
interface ArticleListPresenter {

    fun onArticleClick(article: ArticleModel.DefaultArticle)
}