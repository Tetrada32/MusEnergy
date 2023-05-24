package com.gahov.musenergy.feature.frontpage.presenter

import com.gahov.domain.entities.news.ArticleEntity


interface FrontpagePresenter {

    fun onArticleClick(article: ArticleEntity)
}