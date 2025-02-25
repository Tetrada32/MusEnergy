package com.gahov.domain.repository.articles

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.articles.CategoryEntity

interface SearchCategoriesRepository {

    suspend fun getSearchCategories(): Either<Failure, List<CategoryEntity>>

}