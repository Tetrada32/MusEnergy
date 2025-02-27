package com.gahov.musenergy.data.repository.articles

import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.repository.articles.ArticlesRepository
import com.gahov.musenergy.data.mapper.articles.ArticlesLocalMapper
import com.gahov.musenergy.data.mapper.articles.ArticlesRemoteMapper
import com.gahov.musenergy.data.source.articles.ArticlesLocalSource
import com.gahov.musenergy.data.source.articles.ArticlesRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRepositoryImpl(
    private val remoteSource: ArticlesRemoteSource,
    private val localSource: ArticlesLocalSource,
    private val remoteMapper: ArticlesRemoteMapper,
    private val localMapper: ArticlesLocalMapper
) : ArticlesRepository {

    override suspend fun getArticles(): Either<Failure, List<ArticleEntity>> {
        return when (val localArticles = fetchArticles()) {
            is Either.Left -> loadArticles()
            is Either.Right -> {
                if (localArticles.success.isEmpty()) {
                    loadArticles()
                } else {
                    Either.Right(localArticles.success)
                }
            }
        }
    }

    override suspend fun loadArticles(): Either<Failure, List<ArticleEntity>> {
        return when (val result = remoteSource.loadEverything()) {
            is Either.Left -> result
            is Either.Right -> {
                val articles = remoteMapper.toDomain(result.success.articleResponses)
                localSource.addItems(localMapper.toDatabase(domainModelList = articles))
                Either.Right(success = articles)
            }
        }
    }

    override suspend fun fetchArticles(): Either<Failure, List<ArticleEntity>> {
        return when (val result = localSource.fetchEverything()) {
            is Either.Left -> result
            is Either.Right -> Either.Right(localMapper.toDomain(result.success))
        }
    }

    override suspend fun fetchArticleFlow(id: Long): Flow<Either<Failure, ArticleEntity>> {
        return localSource.fetchArticleByIdFlow(id).map { result ->
            when (result) {
                is Either.Left -> result
                is Either.Right -> Either.Right(localMapper.toDomain(result.success))
            }
        }
    }

    override suspend fun saveArticles(articles: List<ArticleEntity>): Either<Failure, Unit> {
        return localSource.addItems(localMapper.toDatabase(articles))
    }

    override suspend fun loadCategoryArticles(category: SearchNewsCategory): Either<Failure, List<ArticleEntity>> {
        return when (val result = remoteSource.loadCategoryNews(category = category)) {
            is Either.Left -> result
            is Either.Right -> Either.Right(remoteMapper.toDomain(result.success.articleResponses))
        }
    }
}