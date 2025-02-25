package com.gahov.domain.usecase.articles.categories

import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.CategoryEntity
import com.gahov.domain.repository.articles.SearchCategoriesRepository

class LoadSearchCategoriesUseCase(
    private val repository: SearchCategoriesRepository
) : AsyncUseCase<List<CategoryEntity>>() {

    override suspend fun execute(param: UseCase.Params?): Either<Failure, List<CategoryEntity>> {
        return repository.getSearchCategories()
    }
}