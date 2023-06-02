package com.gahov.domain.repository.news

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory

interface NewsRepository {

    suspend fun loadEverything(): Either<Failure, List<ArticleEntity>>

    suspend fun loadCategoryArticles(category: SearchNewsCategory): Either<Failure, List<ArticleEntity>>

}