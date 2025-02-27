package com.gahov.domain.entities.articles.params

import com.gahov.domain.common.usecase.UseCase


data class ArticleDetailsParams(
    val articleId: Long
) : UseCase.Params()