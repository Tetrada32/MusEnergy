package com.gahov.musenergy.feature.articles.factory

import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.musenergy.feature.articles.model.ArticleModel

interface ArticleEntityBuilder {

    fun buildFrontpageList(articleEntityItems: List<ArticleEntity>): List<ArticleModel>

    fun buildCategoriesList(
        category: SearchNewsCategory,
        articleEntityItems: List<ArticleEntity>
    ): List<ArticleModel>
}