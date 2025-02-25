package com.gahov.domain.repository.articles

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory

interface ArticlesRepository {

    suspend fun loadEverything(): Either<Failure, List<ArticleEntity>>

    suspend fun loadCategoryArticles(category: SearchNewsCategory): Either<Failure, List<ArticleEntity>>

}