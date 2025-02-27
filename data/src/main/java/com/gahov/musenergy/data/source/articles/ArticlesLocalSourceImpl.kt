package com.gahov.musenergy.data.source.articles

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.musenergy.data.local.entities.ArticleDTO
import com.gahov.musenergy.data.local.storage.articles.ArticlesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class ArticlesLocalSourceImpl(
    private val articlesDao: ArticlesDao
) : ArticlesLocalSource {

    override suspend fun fetchEverything(): Either<Failure, List<ArticleDTO>> {
        return try {
            Either.Right(articlesDao.fetchAll())
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
    }

    override suspend fun fetchArticleByIdFlow(id: Long): Flow<Either<Failure, ArticleDTO>> {
        return flow {
            articlesDao.getItemById(id).collect {
                emit(Either.Right(success = it))
            }
        }.flowOn(Dispatchers.IO).catch { Either.Left(Failure.DataSourceException(it)) }
    }

    override suspend fun addItems(items: List<ArticleDTO>): Either<Failure, Unit> {
        return try {
            Either.Right(articlesDao.insertItems(items))
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
    }

    override suspend fun deleteEverything(): Either<Failure, Unit> {
        return try {
            Either.Right(articlesDao.deleteAll())
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
    }

    override suspend fun deleteById(id: Long): Either<Failure, Unit> {
        return try {
            Either.Right(articlesDao.deleteFavoriteArticle(id))
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
    }

    override suspend fun updateFavoriteStatus(
        articleId: Long,
        isFavorite: Boolean
    ): Either<Failure, Unit> {
        return try {
            Either.Right(articlesDao.updateFavoriteStatus(id = articleId, isFavorite = isFavorite))
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
    }

    override suspend fun fetchFavoritesFlow(): Flow<Either<Failure, List<ArticleDTO>>> {
        return flow {
            articlesDao.fetchFavorites().collect {
                emit(Either.Right(success = it))
            }
        }.flowOn(Dispatchers.IO).catch { Either.Left(Failure.DataSourceException(it)) }
    }

    override suspend fun deleteAllFavorites(): Either<Failure, Unit> {
        return try {
            Either.Right(articlesDao.deleteFavorites())
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
    }
}