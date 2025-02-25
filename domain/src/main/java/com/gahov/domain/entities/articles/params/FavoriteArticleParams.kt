package com.gahov.domain.entities.articles.params

import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.articles.ArticleEntity


data class FavoriteArticleParams(
    val articleEntity: ArticleEntity
) : UseCase.Params()