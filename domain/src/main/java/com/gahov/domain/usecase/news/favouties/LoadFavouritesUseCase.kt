package com.gahov.domain.usecase.news.favouties

import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.repository.news.NewsRepository

class LoadFavouritesUseCase(private val repository: NewsRepository) :
    AsyncUseCase<List<ArticleEntity>>() {
    //TODO load actually favourites instead of news
    override suspend fun execute(param: UseCase.Params?): Either<Failure, List<ArticleEntity>> {
        return repository.loadCategoryArticles(SearchNewsCategory.CATEGORY_JAZZ)
    }
}