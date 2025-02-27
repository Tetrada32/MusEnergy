package com.gahov.domain.usecase.articles.details

import com.gahov.domain.common.usecase.SubscribeUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.articles.params.ArticleDetailsParams
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.repository.articles.ArticlesRepository
import kotlinx.coroutines.flow.Flow

class FetchArticleDetailsUseCase(private val repository: ArticlesRepository) :
    SubscribeUseCase<ArticleEntity>() {

    override suspend fun execute(param: UseCase.Params?): Flow<Either<Failure, ArticleEntity>> {
        return (param as ArticleDetailsParams).articleId.let { repository.fetchArticleFlow(id = it) }
    }
}