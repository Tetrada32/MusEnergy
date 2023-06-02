package com.gahov.domain.entities.news

import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.search.SearchNewsCategory


data class ArticleCategoryParams(
    val newsCategory: SearchNewsCategory
) : UseCase.Params()