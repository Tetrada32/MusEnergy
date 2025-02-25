package com.gahov.musenergy.data.repository.articles

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.repository.articles.ArticlesRepository
import com.gahov.musenergy.data.mapper.articles.ArticlesRemoteMapper
import com.gahov.musenergy.data.remote.entities.success.ArticleSuccessResponse
import com.gahov.musenergy.data.source.articles.ArticlesRemoteSource

class ArticlesRepositoryImpl(
    private val remoteSource: ArticlesRemoteSource,
    private val articlesRemoteMapper: ArticlesRemoteMapper
) : ArticlesRepository {

    override suspend fun loadEverything(): Either<Failure, List<ArticleEntity>> {
        return when (val result = remoteSource.loadEverything()) {
            is Either.Left -> result
            is Either.Right -> Either.Right(mapApiResponseToDomain(result.success))
        }
    }

    override suspend fun loadCategoryArticles(category: SearchNewsCategory): Either<Failure, List<ArticleEntity>> {
        return when (val result = remoteSource.loadCategoryNews(category = category)) {
            is Either.Left -> result
            is Either.Right -> Either.Right(mapApiResponseToDomain(result.success))
        }
    }

    private fun mapApiResponseToDomain(
        response: ArticleSuccessResponse
    ): List<ArticleEntity> {
        return response.articleResponses?.map { list -> articlesRemoteMapper.toDomain(apiModel = list) }
            ?: emptyList()
    }
}