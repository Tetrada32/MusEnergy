package com.gahov.musenergy.data.repository.news

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.repository.news.NewsRepository
import com.gahov.musenergy.data.mapper.news.ArticleRemoteMapper
import com.gahov.musenergy.data.remote.entities.success.DefaultSuccessResponse
import com.gahov.musenergy.data.source.news.NewsRemoteSource

class NewsRepositoryImpl constructor(
    private val remoteSource: NewsRemoteSource,
    private val articleRemoteMapper: ArticleRemoteMapper
) : NewsRepository {

    override suspend fun loadEverything(): Either<Failure, List<ArticleEntity>> {
        return when (val result = remoteSource.loadEverything()) {
            is Either.Left -> result
            is Either.Right -> Either.Right(mapApiResponseToDomain(result.success))
        }
    }

    private fun mapApiResponseToDomain(
        response: DefaultSuccessResponse
    ): List<ArticleEntity> {
        return response.articleResponses?.map { list -> articleRemoteMapper.toDomain(apiModel = list) }
            ?: emptyList()
    }
}