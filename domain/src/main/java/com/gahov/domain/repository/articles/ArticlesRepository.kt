package com.gahov.domain.repository.articles

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    suspend fun getArticles(): Either<Failure, List<ArticleEntity>>

    suspend fun loadArticles(): Either<Failure, List<ArticleEntity>>

    suspend fun fetchArticles(): Either<Failure, List<ArticleEntity>>

    suspend fun fetchArticleFlow(id: Long): Flow<Either<Failure, ArticleEntity>>

    suspend fun saveArticles(articles: List<ArticleEntity>): Either<Failure, Unit>

    suspend fun loadCategoryArticles(category: SearchNewsCategory): Either<Failure, List<ArticleEntity>>

}