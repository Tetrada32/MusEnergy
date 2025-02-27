package com.gahov.domain.usecase.articles.favorites

import android.content.res.Resources.NotFoundException
import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.articles.params.FavoriteArticleParams
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.repository.favorites.FavoritesRepository
import javax.inject.Inject

class UpdateFavouriteUseCase @Inject constructor(private val repository: FavoritesRepository) :
    AsyncUseCase<Unit>() {

    override suspend fun execute(param: UseCase.Params?): Either<Failure, Unit> {
        return (param as? FavoriteArticleParams)?.let {
            repository.updateFavoriteStatus(
                it.id,
                it.newFavoriteStatus
            )
        } ?: Either.Left(Failure.DataSourceException(NotFoundException()))
    }
}