package com.gahov.domain.usecase.articles.list

import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.params.ArticleCategoryParams
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.repository.articles.ArticlesRepository

class LoadArticleListUseCase(
    private val repository: ArticlesRepository
) : AsyncUseCase<List<ArticleEntity>>() {

    override suspend fun execute(param: UseCase.Params?): Either<Failure, List<ArticleEntity>> {

        val params =
            param as? ArticleCategoryParams ?: ArticleCategoryParams(
                newsCategory = SearchNewsCategory.CATEGORY_ALL
            )

        return repository.loadCategoryArticles(params.newsCategory)
    }
}