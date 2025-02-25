package com.gahov.musenergy.feature.articles.factory

import android.content.Context
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.articles.model.BaseArticleData

interface ArticleEntityBuilder {

    fun buildFrontpageList(articleEntityItems: List<ArticleEntity>): List<ArticleModel>

    fun buildCategoriesList(
        category: SearchNewsCategory,
        articleEntityItems: List<ArticleEntity>
    ): List<ArticleModel>

    //TODO temporary solution
    fun buildDomainArticleFromView(
        context: Context,
        baseArticleData: BaseArticleData?
    ): ArticleEntity
}