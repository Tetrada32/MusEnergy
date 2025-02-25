package com.gahov.domain.usecase.articles.favorites

import com.gahov.domain.common.usecase.SubscribeUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class FetchFavouritesUseCase(private val repository: FavoritesRepository) :
    SubscribeUseCase<List<ArticleEntity>>() {

    override suspend fun execute(param: UseCase.Params?): Flow<Either<Failure, List<ArticleEntity>>> {
        return repository.fetchAllFavorites()
    }
}