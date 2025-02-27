package com.gahov.musenergy.data.source.articles

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.musenergy.data.local.entities.ArticleDTO
import kotlinx.coroutines.flow.Flow

interface ArticlesLocalSource {

    suspend fun fetchEverything(): Either<Failure, List<ArticleDTO>>

    suspend fun fetchArticleByIdFlow(id: Long): Flow<Either<Failure, ArticleDTO>>

    suspend fun addItems(items: List<ArticleDTO>): Either<Failure, Unit>

    suspend fun deleteEverything(): Either<Failure, Unit>

    suspend fun deleteById(id: Long): Either<Failure, Unit>


    suspend fun updateFavoriteStatus(articleId: Long, isFavorite: Boolean): Either<Failure, Unit>

    suspend fun fetchFavoritesFlow(): Flow<Either<Failure, List<ArticleDTO>>>

    suspend fun deleteAllFavorites(): Either<Failure, Unit>
}