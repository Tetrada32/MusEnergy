package com.gahov.domain.repository.news

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.CategoryEntity

interface SearchCategoriesRepository {

    suspend fun getSearchCategories(): Either<Failure, List<CategoryEntity>>

}