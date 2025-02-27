package com.gahov.domain.repository.favorites

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun fetchAllFavorites(): Flow<Either<Failure, List<ArticleEntity>>>

    suspend fun updateFavoriteStatus(articleId: Long, isFavorite: Boolean): Either<Failure, Unit>

    suspend fun deleteFavorite(id: Long): Either<Failure, Unit>

    suspend fun deleteAllFavorites(): Either<Failure, Unit>

}