package com.gahov.domain.usecase.news.frontpage

import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.repository.news.NewsRepository

class LoadFrontpageUseCase(
    private val repository: NewsRepository
) : AsyncUseCase<List<ArticleEntity>>() {

    override suspend fun execute(param: UseCase.Params?): Either<Failure, List<ArticleEntity>> {
        return repository.loadEverything()
    }
}