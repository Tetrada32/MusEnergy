package com.gahov.domain.usecase.articles.frontpage

import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.repository.articles.ArticlesRepository

class LoadFrontpageUseCase(
    private val repository: ArticlesRepository
) : AsyncUseCase<List<ArticleEntity>>() {

    override suspend fun execute(param: UseCase.Params?): Either<Failure, List<ArticleEntity>> {
        return repository.loadEverything()
    }
}