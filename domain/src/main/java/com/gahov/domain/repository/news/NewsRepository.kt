package com.gahov.domain.repository.news

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleEntity

interface NewsRepository {

    suspend fun loadEverything(): Either<Failure, List<ArticleEntity>>

}