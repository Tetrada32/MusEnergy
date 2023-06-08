package com.gahov.musenergy.data.source.news

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.musenergy.data.remote.entities.success.ArticleSuccessResponse

interface NewsRemoteSource {

    suspend fun loadEverything(): Either<Failure, ArticleSuccessResponse>

    suspend fun loadCategoryNews(category: SearchNewsCategory): Either<Failure, ArticleSuccessResponse>
}