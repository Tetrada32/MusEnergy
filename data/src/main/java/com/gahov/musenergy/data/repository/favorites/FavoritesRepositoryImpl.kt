package com.gahov.musenergy.data.repository.favorites

import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.repository.favorites.FavoritesRepository
import com.gahov.musenergy.data.mapper.articles.ArticlesLocalMapper
import com.gahov.musenergy.data.source.articles.ArticlesLocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val localSource: ArticlesLocalSource,
    private val localMapper: ArticlesLocalMapper
) : FavoritesRepository {

    override suspend fun fetchAllFavorites(): Flow<Either<Failure, List<ArticleEntity>>> {
        return localSource.fetchFavorites().map { result ->
            when (result) {
                is Either.Left -> result
                is Either.Right -> Either.Right(localMapper.toDomain(result.success))
            }
        }
    }

    override suspend fun addToFavorites(articleEntity: ArticleEntity): Either<Failure, Unit> {
        articleEntity.isFavorite = true
        return localSource.addItems(listOf(localMapper.toDatabase(articleEntity)))
    }


    override suspend fun deleteFavorite(id: Long): Either<Failure, Unit> {
        return localSource.deleteById(id)
    }

    override suspend fun deleteAllFavorites(): Either<Failure, Unit> {
        return localSource.deleteAllFavorites()
    }
}