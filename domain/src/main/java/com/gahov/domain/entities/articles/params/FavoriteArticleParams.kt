package com.gahov.domain.entities.articles.params

import com.gahov.domain.common.usecase.UseCase


data class FavoriteArticleParams(
    val id: Long,
    val newFavoriteStatus: Boolean
) : UseCase.Params()