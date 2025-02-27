package com.gahov.musenergy.data.source.articles

import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.musenergy.data.local.entities.ArticleDTO
import com.gahov.musenergy.data.local.storage.articles.ArticlesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArticlesLocalSourceImpl(
    private val articlesDao: ArticlesDao,
    private val logger: Logger
) : ArticlesLocalSource {

    override suspend fun fetchEverything(): Either<Failure, List<ArticleDTO>> {
        return try {
            Either.Right(articlesDao.selectAll())
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
    }

    override suspend fun fetchById(id: Long): Either<Failure, ArticleDTO> {
        return try {
            Either.Right(articlesDao.getItemById(id))
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
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

    override suspend fun fetchFavorites(): Flow<Either<Failure, List<ArticleDTO>>> {
        logger.log(message = "Favorites Local Source() request")

        return flow {
            articlesDao.fetchFavorites().collect {
                emit(Either.Right(success = it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteAllFavorites(): Either<Failure, Unit> {
        return try {
            Either.Right(articlesDao.deleteFavorites())
        } catch (e: Exception) {
            Either.Left(Failure.DataSourceException(e))
        }
    }
}