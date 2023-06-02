package com.gahov.domain.usecase.news.list

import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleCategoryParams
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.repository.news.NewsRepository

class LoadArticleListUseCase(
    private val repository: NewsRepository
) : AsyncUseCase<List<ArticleEntity>>() {

    override suspend fun execute(param: UseCase.Params?): Either<Failure, List<ArticleEntity>> {

        val params =
            param as? ArticleCategoryParams ?: ArticleCategoryParams(
                newsCategory = SearchNewsCategory.CATEGORY_ALL
            )

        return repository.loadCategoryArticles(params.newsCategory)
    }
}